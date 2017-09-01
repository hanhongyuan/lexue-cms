package com.lexue.sso.web.controller;

import com.alibaba.fastjson.JSON;
import com.lexue.base.annotation.SystemControllerLog;
import com.lexue.base.annotation.ssoauth.Permission;
import com.lexue.base.domain.*;
import com.lexue.base.util.DateUtils;
import com.lexue.base.util.ResponseResult;
import com.lexue.sso.web.feignclient.*;
import com.lexue.sso.web.webconfig.WebConfig;
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

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * author lilong
 */
@Controller
@RequestMapping("/adFrame")
public class AdFrameController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    AdFrameFeignClient adFrameFeignClient;
    @Autowired
    AdTemplateFeignClient adTemplateFeignClient;
    @Autowired
    AdResourceFeignClient adResourceFeignClient;
    @Autowired
    AdBoxFeignClient adBoxFeignClient;

    @Autowired
    AdvertisementManagerFeignClient advertisementManagerFeignClient;

    @SystemControllerLog(module = "广告帧管理",method = "广告帧列表")
    @Permission("sys:adframe:list")
    @RequestMapping(value ={"/","/list"},method = RequestMethod.GET)
    public String index(){
        return "ad/adFrame_list";
    }

    @SystemControllerLog(module = "广告帧管理",method = "新增广告帧")
    @Permission("sys:adframe:add")
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(String id,Model model){
        model.addAttribute("adTpl",adTemplateFeignClient.findAll(getClient()).getData());
        model.addAttribute("adRes",adResourceFeignClient.findAll(getClient()).getData());
        if(id!=null){
            model.addAttribute("adFrame",adFrameFeignClient.getAdFrameAll(id).getData());
        }else
            model.addAttribute("adFrame",new AdFrames());
        return "ad/adFrame_add";
    }
    @RequestMapping(value = "/data",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<AdFrames> data(@RequestParam(value = "pageIndex",defaultValue ="1")Integer pageIndex,
                                       @RequestParam(value = "pageSize",defaultValue = "15")Integer pageSize){
        ResponseResult<AdFrames> data=new ResponseResult<AdFrames>();
        logger.info("AdBoxsController init PageData");
        List<AdFrames> list=adFrameFeignClient.findPage(pageIndex,pageSize,getClient()).getData();
        data.setList(list);
        data.setRel(true);
        data.setCount(adFrameFeignClient.findPageCount(getClient()).getData());
        return data;
    }

    @SystemControllerLog(module = "广告帧管理",method = "保存广告帧")
    @Permission("sys:adframe:save")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(AdFrames adFrames){
        logger.info("AdBoxsController save start");
        if(adFrames.getId()==null||"".equals(adFrames.getId())){
            String id= UUID.randomUUID().toString().replace("-","");
            adFrames.setId(id);
            adFrames.setClient(getClient());
            adFrames.setUpdateTime(new Date().getTime());
            adFrames.setItemCapacity((byte) 1);
            adFrames.setEnableTimestamp(DateUtils.strConvertToLong(adFrames.getStartTime()));
            adFrames.setDisableTimestamp(DateUtils.strConvertToLong(adFrames.getEndTime()));
            adFrames.setUpName(getLoginUser().getLoginName());
            adFrames.setStatus((byte)1);
            adFrameFeignClient.addAdFrame(adFrames);
            AdFrames old=adFrameFeignClient.getAdFrame(id).getData();
            AdRela adRela=new AdRela();
            adRela.setCellLayoutXEnd(adFrames.getCellLayoutXEnd());
            adRela.setCellLayoutXStart(adFrames.getCellLayoutXStart());
            adRela.setCellLayoutYEnd(adFrames.getCellLayoutYEnd());
            adRela.setCellLayoutYStart(adFrames.getCellLayoutYStart());
            adRela.setClient(getClient());
            adRela.setFrameId(old.getFrameId());
            adRela.setPriority(adFrames.getPriority());
            adRela.setResourceId(adFrames.getAdResId());
            adRela.setStatus((byte)1);
            adFrameFeignClient.addAdRela(adRela);
        }else{
            AdFrames old=adFrameFeignClient.getAdFrame(adFrames.getId()).getData();
            adFrames.setUpdateTime(new Date().getTime());
            adFrames.setEnableTimestamp(DateUtils.strConvertToLong(adFrames.getStartTime()));
            adFrames.setDisableTimestamp(DateUtils.strConvertToLong(adFrames.getEndTime()));
            adFrames.setUpName(getLoginUser().getLoginName());
            adFrameFeignClient.updateAdFrame(adFrames);
            AdRela adRela=adFrameFeignClient.getAdRelaToFarmeId(old.getFrameId()+"").getData();
            adRela.setCellLayoutXEnd(adFrames.getCellLayoutXEnd());
            adRela.setCellLayoutXStart(adFrames.getCellLayoutXStart());
            adRela.setCellLayoutYEnd(adFrames.getCellLayoutYEnd());
            adRela.setCellLayoutYStart(adFrames.getCellLayoutYStart());
            adRela.setPriority(adFrames.getPriority());
            adRela.setResourceId(adFrames.getAdResId());
            adRela.setStatus(adFrames.getStatus());
            adFrameFeignClient.updateAdRela(adRela);
        }
        return "success";
    }

    @SystemControllerLog(module = "广告帧管理",method = "删除广告帧")
    @Permission("sys:adframe:delete")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public void delete(String id){
        logger.info("AdBoxsController delete id is {}"+id);
        adFrameFeignClient.deleteAdFrame(id);
    }
    @SystemControllerLog(module = "广告帧管理",method = "更新广告状态")
    @RequestMapping(value = "/updateStatus",method = RequestMethod.POST)
    @ResponseBody
    public String updateStatus(String id,int status){
        AdFrames adFrames =adFrameFeignClient.getAdFrame(id).getData();
        adFrames.setStatus((byte) status);
        adFrames.setUpName(getLoginUser().getLoginName());
        AdBox adBox=fomartAdBoxEntity(adFrames);
        if(status==2){
            AdRela adRela=adFrameFeignClient.getAdRelaToFarmeId(adFrames.getFrameId()+"").getData();
            AdRes adRes=adResourceFeignClient.getAdResourceToRsId(adRela.getResourceId()+"").getData();
            if(adRes.getStatus()==1){
                return "adres";
            }
            AdFilters adFilters=adTemplateFeignClient.getAdFilterToTplId(adFrames.getTplId()+"").getData();
            if(adFilters.getStatus()==1){
                return "adfilter";
            }
            AdBoxs adBoxs=adBoxFeignClient.getAdBoxsToFmId(adFrames.getFrameId()+"").getData();
            if(adBoxs.getStat()==1){
                return "adbox";
            }
        }
        logger.info("JSON POST STR IS  "+ JSON.toJSONString(adBox));
        ManagerResult<Boolean> managerResult=advertisementManagerFeignClient.syncAdvertisementManager(adBox);
        logger.info("code:"+managerResult.getCode()+"  msg:"+managerResult.getMsg()+"    result:"+managerResult.getBody());
        if(managerResult.getCode()==200){
            adFrameFeignClient.updateAdFrame(adFrames);
        }
        return "success";
    }

    public AdBox fomartAdBoxEntity(AdFrames adFrames){
        AdBox adBox=new AdBox();
        AdFilters adFilters=adTemplateFeignClient.getAdFilterToTplId(adFrames.getTplId()+"").getData();
        AdBoxs adboxs=adBoxFeignClient.getAdBoxsToBoxId(adFilters.getBoxId()+"").getData();
        BeanUtils.copyProperties(adboxs,adBox);
        adBox.setStatus(adboxs.getStat());
        List<AdFilters> adFilters1= adTemplateFeignClient.findAllFilterToBoxId(adboxs.getBoxId()+"").getData();
        for(AdFilters filters:adFilters1){
            //if(filters.getStatus()==2){
                AdFilter adFilter=new AdFilter();
                AdTemplate adTemplate=new AdTemplate();
                BeanUtils.copyProperties(filters,adFilter);
                AdTemplates adTemplates=adTemplateFeignClient.getAdTpl(filters.getTplid()+"").getData();
                BeanUtils.copyProperties(adTemplates,adTemplate);
                adFilter.setTemplate(adTemplate);
                List<AdFrames> frames=adFrameFeignClient.getAdFrameToTplId(adTemplates.getTemplateId()+"").getData();
                for(AdFrames adFram:frames){
                    //if(adFram.getStatus()==2){
                        AdFrame adFrame=new AdFrame();
                        AdFrameRelation adFrameRelation=new AdFrameRelation();
                        AdResource adResource=new AdResource();
                        BeanUtils.copyProperties(adFram,adFrame);
                        AdRela adrela= adFrameFeignClient.getAdRelaToFarmeId(adFram.getFrameId()+"").getData();
                        AdRes adres=adResourceFeignClient.getAdResourceToRsId(adrela.getResourceId()+"").getData();
                        BeanUtils.copyProperties(adrela,adFrameRelation);
                        adFrameRelation.setStatus(adFram.getStatus());
                        if(adres.getStatus()==2){
                            BeanUtils.copyProperties(adres,adResource);
                            adFrameRelation.setAdResource(adResource);
                        }
                        adFrame.getRelations().add(adFrameRelation);
                        adTemplate.getAdFrames().add(adFrame);
                   // }
                }
                adFilter.setTemplate(adTemplate);
                adBox.getFilters().add(adFilter);
            }
      //  }
        return adBox;
    }
}