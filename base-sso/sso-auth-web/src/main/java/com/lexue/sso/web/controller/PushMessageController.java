package com.lexue.sso.web.controller;

import com.alibaba.fastjson.JSON;
import com.lexue.base.annotation.SystemControllerLog;
import com.lexue.base.annotation.ssoauth.Permission;
import com.lexue.base.domain.PushMessage;
import com.lexue.base.util.DateUtils;
import com.lexue.base.util.ResponseResult;
import com.lexue.push.common.type.ProgressStatus;
import com.lexue.push.common.type.TargetType;
import com.lexue.sso.web.feignclient.NotificationManagerFeginClient;
import com.lexue.sso.web.feignclient.PushMessageFeignClient;
import com.lexue.sso.web.feignclient.VideoFeignClient;
import com.lexue.type.BusinessType;
import com.lexue.type.DataStatus;
import main.java.com.lexue.push.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * author lilong
 */
@Controller
@RequestMapping("/push")
public class PushMessageController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    PushMessageFeignClient pushMessageFeignClient;
    @Autowired
    VideoFeignClient videoFeignClient;
    @Autowired
    NotificationManagerFeginClient notificationManagerFeginClient;

    @SystemControllerLog(module = "消息推送管理",method = "消息推送列表")
    @Permission("sys:push:list")
    @RequestMapping(value ={"/","/list"},method = RequestMethod.GET)
    public String index(){
        return "push/list";
    }

    @SystemControllerLog(module = "消息推送管理",method = "新增消息推送")
    @Permission("sys:push:add")
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(String id,Model model){
        model.addAttribute("videoList",videoFeignClient.findAllVideo(getClient()).getData());
        if(id!=null){
            model.addAttribute("pushMessage",pushMessageFeignClient.getPushMessage(id).getData());
        }else
            model.addAttribute("pushMessage",new PushMessage());
        return "push/add";
    }
    @RequestMapping(value = "/data",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<PushMessage> data(@RequestParam(value = "pageIndex",defaultValue ="1")Integer pageIndex,
                                       @RequestParam(value = "pageSize",defaultValue = "15")Integer pageSize){
        ResponseResult<PushMessage> data=new ResponseResult<PushMessage>();
        logger.info("PushMessageController init PageData");
        List<PushMessage> list=pushMessageFeignClient.findPage(pageIndex,pageSize,getClient()).getData();
        for(PushMessage pushMessage:list){
            pushMessage.setStartTime(DateUtils.formatLongToString(pushMessage.getPushTime()));
            pushMessage.setEndTime(DateUtils.formatLongToString(pushMessage.getExpireTime()));
        }
        data.setList(list);
        data.setRel(true);
        data.setCount(pushMessageFeignClient.findPageCount(getClient()).getData());
        return data;
    }
    @SystemControllerLog(module = "消息推送管理",method = "保存消息推送")
    @Permission("sys:push:save")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(PushMessage pushMessage){
        logger.info("PushMessageController save start");
        pushMessage.setBusinessType(BusinessType.parser(pushMessage.getBType()));
        if("ALL".equals(pushMessage.getTargetTypes())){
            pushMessage.setTargetType(TargetType.ALL);
        }else if("PLANTFORM".equals(pushMessage.getTargetTypes())){
            pushMessage.setTargetType(TargetType.PLANTFORM);
            pushMessage.setValue(pushMessage.getPlantforms());
        }else if("USERID".equals(pushMessage.getTargetTypes())){
            pushMessage.setTargetType(TargetType.USERID);
            pushMessage.setValue(pushMessage.getUser());
        }else if("TAG".equals(pushMessage.getTargetTypes())){
            pushMessage.setTargetType(TargetType.TAG);
            pushMessage.setValue(pushMessage.getTag());
        }
        if(pushMessage.getTransparent()==2){
            pushMessage.setTransmission(pushMessage.getVideoId());
        }
        pushMessage.setCreateTime(new Date().getTime());
        pushMessage.setPushTime(DateUtils.strConvertToLong(pushMessage.getStartTime()));
        pushMessage.setExpireTime(DateUtils.strConvertToLong(pushMessage.getEndTime()));
        pushMessage.setClient(getClient());
        String id= UUID.randomUUID().toString().replace("-","");
        if("on".equals(pushMessage.getSOffline())){
            pushMessage.setStoreOffline(true);
        }else {
            pushMessage.setStoreOffline(false);
        }
        if(pushMessage.getId()==null||"".equals(pushMessage.getId())){
            pushMessage.setId(id);
            pushMessageFeignClient.addRuleInfo(pushMessage);
        }else{
            pushMessageFeignClient.updateRuleInfo(pushMessage);
        }
        pushMessage=pushMessageFeignClient.getPushMessage(id).getData();
        ManagerResult<Long> result= notificationManagerFeginClient.syncPushPlanInfo(initRuleInfo(pushMessage));
        logger.info("result is code:"+result.getCode()+" body:"+result.getBody()+" msg:"+result.getMsg());
        if(result.getCode()==200){
            pushMessage.setPushId(result.getBody().toString());
            pushMessage.setPushStatus("INIT");
            pushMessageFeignClient.updateRuleInfo(pushMessage);
        }
        return "success";
    }
    @RequestMapping(value = "/updateStatus",method = RequestMethod.POST)
    @ResponseBody
    public String updateStatus(String id,int status){
        if(status==1){
            PushMessage pushMessage=pushMessageFeignClient.getPushMessage(id).getData();
            ManagerResult<Long> result= notificationManagerFeginClient.syncPushPlanInfo(initRuleInfo(pushMessage));
            logger.info("result is code:"+result.getCode()+" body:"+result.getBody()+" msg:"+result.getMsg());
            if(result.getCode()==200){
                pushMessage.setPushId(result.getBody().toString());
                pushMessage.setPushStatus("INIT");
                pushMessageFeignClient.updateRuleInfo(pushMessage);
            }
        }else {
            PushMessage pushMessage=pushMessageFeignClient.getPushMessage(id).getData();
            ManagerResult<PushTaskInfo> result= notificationManagerFeginClient.getPushTask(Long.parseLong(pushMessage.getPushId()));
            logger.info("result is code:"+result.getCode()+" body:"+result.getBody()+" msg:"+result.getMsg());
            if(result.getCode()==200){
                pushMessage.setPushStatus(result.getBody().getStatus().name());
                pushMessageFeignClient.updateRuleInfo(pushMessage);
            }
        }
        return "success";
    }
    @SystemControllerLog(module = "消息推送管理",method = "删除消息推送")
    @Permission("sys:push:delete")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public void delete(String id){
        logger.info("PushMessageController delete id is {}"+id);
        pushMessageFeignClient.deleteRuleInfo(id);
    }
    public PushPlanInfo initRuleInfo(PushMessage pushMessage){
        PushPlanInfo pushPlanInfo=new PushPlanInfo();
        MessageInfo messageInfo=new MessageInfo();
        RuleInfo ruleInfo=new RuleInfo();
        List<String> value=new ArrayList<>();
        value.add(pushMessage.getValue()==null?"":pushMessage.getValue());
        BeanUtils.copyProperties(pushMessage,ruleInfo);
        BeanUtils.copyProperties(pushMessage,messageInfo);
        ruleInfo.setTargetValue(value);
        ruleInfo.setStatus(DataStatus.parser(Integer.parseInt(pushMessage.getStatus())));
        messageInfo.setTransmission(pushMessage.getTransm());
        pushPlanInfo.setMessage(messageInfo);
        pushPlanInfo.setRule(ruleInfo);
        logger.info("JSON IS {}"+ JSON.toJSONString(pushPlanInfo));
        return pushPlanInfo;
    }
}