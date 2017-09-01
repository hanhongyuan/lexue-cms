package com.lexue.sso.web.controller;

import com.alibaba.fastjson.JSON;
import com.lexue.base.annotation.SystemControllerLog;
import com.lexue.base.annotation.ssoauth.Permission;
import com.lexue.base.domain.*;
import com.lexue.base.model.AdFilterTplModel;
import com.lexue.base.util.ResponseResult;
import com.lexue.sso.web.feignclient.*;
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
@RequestMapping("/adTpl")
public class AdTemplateController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    AdTemplateFeignClient adTemplateFeignClient;
    @Autowired
    AdBoxFeignClient adBoxFeignClient;
    @Autowired
    AdFrameFeignClient adFrameFeignClient;
    @Autowired
    AdResourceFeignClient adResourceFeignClient;
    @Autowired
    AdvertisementManagerFeignClient advertisementManagerFeignClient;

    @SystemControllerLog(module = "广告模板管理",method = "广告模板列表")
    @Permission("sys:adtpl:list")
    @RequestMapping(value ={"/","/list"},method = RequestMethod.GET)
    public String index(){
        return "ad/adTpl_list";
    }

    @SystemControllerLog(module = "广告模板管理",method = "新增广告模板")
    @Permission("sys:adtpl:add")
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(String id,Model model){
        model.addAttribute("adBox",adBoxFeignClient.findAll(getClient()).getData());
        if(id!=null){
            model.addAttribute("adFilter",adTemplateFeignClient.getAdFilterTpl(id).getData());
        }else
            model.addAttribute("adFilter",new AdFilterTplModel());
        return "ad/adTpl_add";
    }
    @RequestMapping(value = "/data",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<AdTemplates> data(@RequestParam(value = "pageIndex",defaultValue ="1")Integer pageIndex,
                                       @RequestParam(value = "pageSize",defaultValue = "15")Integer pageSize){
        ResponseResult<AdTemplates> data=new ResponseResult<AdTemplates>();
        logger.info("AdTemplateController init PageData");
        List<AdTemplates> list=adTemplateFeignClient.findPage(pageIndex,pageSize,getClient()).getData();
        data.setList(list);
        data.setRel(true);
        data.setCount(adTemplateFeignClient.findPageCount(getClient()).getData());
        return data;
    }

    @SystemControllerLog(module = "广告模板管理",method = "保存广告模板")
    @Permission("sys:adtpl:save")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(AdFilterTplModel adTemplates){
        logger.info("AdTemplateController save start");
        if(adTemplates.getTplid()==0){
            String id= UUID.randomUUID().toString().replace("-","");
            AdTemplates adTemplate=new AdTemplates();
            adTemplate.setFrameCapacity((byte) adTemplates.getFrameCapacity());
            adTemplate.setFramePickType((byte)adTemplates.getFramePickType());
            adTemplate.setFrameSwitchTime((byte)adTemplates.getFrameSwitchTime());
            adTemplate.setTitle(adTemplates.getTitle());
            adTemplate.setUpdateTime(new Date().getTime());
            adTemplate.setNote(adTemplates.getNote());
            adTemplate.setClient(getClient());
            adTemplate.setId(id);
            adTemplateFeignClient.addAdTpl(adTemplate);
            adTemplate=adTemplateFeignClient.getAdTplToId(id).getData();
            AdFilters adFilters=new AdFilters();
            adFilters.setBoxId(adTemplates.getBoxId());
            adFilters.setClient(getClient());
            adFilters.setTplid(adTemplate.getTemplateId());
            adFilters.setStatus((byte)adTemplates.getStatus());
            if(adTemplates.getDefaultFilter()==0){
                adFilters.setConditionMap(adTemplates.getConditionMap().replace("'","\""));
                adFilters.setLogic((byte) adTemplates.getLogic());
                adFilters.setDefaultFilter((byte)adTemplates.getDefaultFilter());
                adFilters.setPriority((byte)adTemplates.getPriority());
            }else{
                adFilters.setDefaultFilter((byte)adTemplates.getDefaultFilter());
                adFilters.setConditionMap("{}");
                adFilters.setLogic((byte)1);
                adFilters.setPriority((byte)0);
            }
            adFilters.setUpdateTime(new Date().getTime());
            adTemplateFeignClient.addFilter(adFilters);
        }else {
            AdFilters adFilters=adTemplateFeignClient.getAdFilter(adTemplates.getFilterId().toString()).getData();
            AdTemplates adTemplates1=adTemplateFeignClient.getAdTpl(adTemplates.getTplid()+"").getData();
            adFilters.setBoxId(adTemplates.getBoxId());
            adFilters.setTplid(adTemplates1.getTemplateId());
            adFilters.setUpdateTime(new Date().getTime());
            adFilters.setStatus((byte)adTemplates.getStatus());
            if(adTemplates.getDefaultFilter()==0){
                adFilters.setLogic((byte) adTemplates.getLogic());
                adFilters.setDefaultFilter((byte)adTemplates.getDefaultFilter());
                adFilters.setPriority((byte)adTemplates.getPriority());
                adFilters.setConditionMap(adTemplates.getConditionMap().replace("'","\""));
            }else
                adFilters.setDefaultFilter((byte)adTemplates.getDefaultFilter());
            //
            adTemplates1.setFrameCapacity((byte) adTemplates.getFrameCapacity());
            adTemplates1.setFramePickType((byte)adTemplates.getFramePickType());
            adTemplates1.setFrameSwitchTime((byte)adTemplates.getFrameSwitchTime());
            adTemplates1.setTitle(adTemplates.getTitle());
            adTemplates1.setUpdateTime(new Date().getTime());
            adTemplates1.setNote(adTemplates.getNote());
            if(adFilters.getStatus()==1){
                AdBox adBox=new AdBox();
                AdBoxs adboxs=adBoxFeignClient.getAdBoxsToBoxId(adFilters.getBoxId()+"").getData();
                BeanUtils.copyProperties(adboxs,adBox);
                adBox.setStatus(adboxs.getStat());
                List<AdFilters> adFilters1= adTemplateFeignClient.findAllFilterToBoxId(adboxs.getBoxId()+"").getData();
                for(AdFilters filters:adFilters1){
                    //if(filters.getStatus()==2){
                        AdTemplate adTemplate=new AdTemplate();
                        AdFilter adFilter=new AdFilter();
                        BeanUtils.copyProperties(filters,adFilter);
                        AdTemplates adTemp=adTemplateFeignClient.getAdTpl(filters.getTplid()+"").getData();
                        BeanUtils.copyProperties(adTemp,adTemplate);
                        adFilter.setTemplate(adTemplate);
                        List<AdFrames> frames=adFrameFeignClient.getAdFrameToTplId(adTemp.getTemplateId()+"").getData();
                        for(AdFrames adFram:frames){
                           // if(adFram.getStatus()==2){
                                AdResource adResource=new AdResource();
                                AdFrame adFrame=new AdFrame();
                                AdFrameRelation adFrameRelation=new AdFrameRelation();
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
                  //  }
                }
                logger.info("JSON POST STR IS  "+ JSON.toJSONString(adBox));
                ManagerResult<Boolean> managerResult=advertisementManagerFeignClient.syncAdvertisementManager(adBox);
                logger.info("code:"+managerResult.getCode()+"  msg:"+managerResult.getMsg()+"    result:"+managerResult.getBody());
                if(managerResult.getCode()==200){
                    adTemplateFeignClient.updateAdFilter(adFilters);
                    adTemplateFeignClient.updateAdTpl(adTemplates1);
                    List<AdFrames> frames=adFrameFeignClient.getAdFrameToTplId(adFilters.getTplid()+"").getData();
                    for(AdFrames adFram:frames){
                        adFram.setStatus((byte)1);
                        adFrameFeignClient.updateAdFrame(adFram);
                    }
                }
            }else {
                adTemplateFeignClient.updateAdFilter(adFilters);
                adTemplateFeignClient.updateAdTpl(adTemplates1);
            }
        }
        return "success";
    }

    @SystemControllerLog(module = "广告模板管理",method = "删除广告模板")
    @Permission("sys:adtpl:delete")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public void delete(String id){
        logger.info("AdTemplateController delete id is {}"+id);
        adTemplateFeignClient.deleteAdTpl(id);
    }
}