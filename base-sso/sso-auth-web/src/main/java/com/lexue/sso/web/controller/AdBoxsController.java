package com.lexue.sso.web.controller;

import com.alibaba.fastjson.JSON;
import com.lexue.base.annotation.SystemControllerLog;
import com.lexue.base.annotation.ssoauth.Permission;
import com.lexue.base.domain.*;
import com.lexue.base.util.ResponseResult;
import com.lexue.sso.web.feignclient.AdBoxFeignClient;
import com.lexue.sso.web.feignclient.AdFrameFeignClient;
import com.lexue.sso.web.feignclient.AdTemplateFeignClient;
import com.lexue.sso.web.feignclient.AdvertisementManagerFeignClient;
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

/**
 * author lilong
 */
@Controller
@RequestMapping("/adBox")
public class AdBoxsController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    AdBoxFeignClient adBoxFeignClient;
    @Autowired
    AdTemplateFeignClient adTemplateFeignClient;
    @Autowired
    AdFrameFeignClient adFrameFeignClient;
    @Autowired
    AdvertisementManagerFeignClient advertisementManagerFeignClient;

    @SystemControllerLog(module = "广告位管理",method = "广告位列表")
    @Permission("sys:adbox:list")
    @RequestMapping(value ={"/","/list"},method = RequestMethod.GET)
    public String index(){
        return "ad/adbox_list";
    }

    @SystemControllerLog(module = "广告位管理",method = "添加广告位")
    @Permission("sys:adbox:add")
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(String id,Model model){
        if(id!=null){
            model.addAttribute("adBoxs",adBoxFeignClient.getAdBoxs(id).getData());
        }else
            model.addAttribute("adBoxs",new AdBoxs());
        return "ad/adbox_add";
    }
    @RequestMapping(value = "/data",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<AdBoxs> data(@RequestParam(value = "pageIndex",defaultValue ="1")Integer pageIndex,
                                      @RequestParam(value = "pageSize",defaultValue = "15")Integer pageSize){
        ResponseResult<AdBoxs> data=new ResponseResult<AdBoxs>();
        logger.info("AdBoxsController init PageData");
        List<AdBoxs> list=adBoxFeignClient.findPage(pageIndex,pageSize,getClient()).getData();
        data.setList(list);
        data.setRel(true);
        data.setCount(adBoxFeignClient.findPageCount(getClient()).getData());
        return data;
    }
    @SystemControllerLog(module = "广告位管理",method = "保存广告位")
    @Permission("sys:adbox:save")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(AdBoxs adBoxs){
        logger.info("AdBoxsController save start");
        adBoxs.setUpdateTime(new Date().getTime());
        adBoxs.setStat(adBoxs.getStatus().byteValue());
        adBoxs.setClient(getClient());
        adBoxs.setUnit(adBoxs.getCompany().byteValue());
        adBoxs.setUpName(getLoginUser().getLoginName());
        if(adBoxs.getId()==null||"".equals(adBoxs.getId())){
            adBoxFeignClient.addAdBox(adBoxs);
        }else{
            //adBoxFeignClient.updateAdBox(adBoxs);
            AdBoxs oldbox=adBoxFeignClient.getAdBoxs(adBoxs.getId()).getData();
            if(adBoxs.getStat()==1){
                AdBox adBox=new AdBox();
                BeanUtils.copyProperties(adBoxs,adBox);
                adBox.setBoxId(oldbox.getBoxId());
                adBox.setStatus(adBoxs.getStat());
                logger.info("JSON POST STR IS  "+ JSON.toJSONString(adBox));
                ManagerResult<Boolean> result= advertisementManagerFeignClient.syncAdvertisementManager(adBox);
                logger.info("code:"+result.getCode()+"  msg:"+result.getMsg()+"    result:"+result.getBody());
                if(result.getCode()==200){
                    adBoxFeignClient.updateAdBox(adBoxs);
                    List<AdFilters> adFilters1= adTemplateFeignClient.findAllFilterToBoxId(oldbox.getBoxId()+"").getData();
                    for(AdFilters filters:adFilters1){
                        List<AdFrames> frames=adFrameFeignClient.getAdFrameToTplId(filters.getTplid()+"").getData();
                        for(AdFrames adFram:frames){
                            adFram.setStatus((byte)1);
                            adFrameFeignClient.updateAdFrame(adFram);
                        }
                    }
                }
            }else
                adBoxFeignClient.updateAdBox(adBoxs);
        }
        return "success";
    }
    @SystemControllerLog(module = "广告位管理",method = "删除广告位")
    @Permission("sys:adbox:delete")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public void delete(String id){
        logger.info("AdBoxsController delete id is {}"+id);
        adBoxFeignClient.deleteAdBox(id);
    }
}