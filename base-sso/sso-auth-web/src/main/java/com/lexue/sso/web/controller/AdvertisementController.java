package com.lexue.sso.web.controller;

import com.lexue.base.domain.Advertisement;
import com.lexue.base.util.ResponseResult;
import com.lexue.sso.web.feignclient.AdBoxFeignClient;
import com.lexue.sso.web.feignclient.AdResourceFeignClient;
import com.lexue.sso.web.feignclient.AdvertisementFeignClient;
import com.lexue.sso.web.webconfig.WebConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * author lilong
 */
@Controller
@RequestMapping("/ad")
public class AdvertisementController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    AdvertisementFeignClient advertisementFeignClient;
    @Autowired
    AdBoxFeignClient adBoxFeignClient;
    @Autowired
    AdResourceFeignClient adResourceFeignClient;
    @Autowired
    WebConfig webConfig;

    @RequestMapping(value ={"/","/list"},method = RequestMethod.GET)
    public String index(){
        return "ad/adver_list";
    }
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(String id,Model model){
        if(id!=null){
            model.addAttribute("advertisement",advertisementFeignClient.getAdvertisement(id).getData());
        }else
            model.addAttribute("advertisement",new Advertisement());
        model.addAttribute("adBox",adBoxFeignClient.findAll(getClient()));
        model.addAttribute("adRes",adResourceFeignClient.findAll(getClient()));
        return "ad/adver_add";
    }
    @RequestMapping(value = "/data",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Advertisement> data(@RequestParam(value = "pageIndex",defaultValue ="1")Integer pageIndex,
                                     @RequestParam(value = "pageSize",defaultValue = "15")Integer pageSize){
        ResponseResult<Advertisement> data=new ResponseResult<Advertisement>();
        logger.info("AdvertisementController init PageData");
        List<Advertisement> list=advertisementFeignClient.findPage(pageIndex,pageSize).getData();
        data.setList(list);
        data.setRel(true);
        data.setCount(advertisementFeignClient.findPageCount().getData());
        return data;
    }
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(Advertisement advertisement){
        logger.info("Advertisement save start");
        if(advertisement.getAdId()==null||"".equals(advertisement.getAdId())){
            advertisement.setDelFlag("0");
            advertisement.setClient(webConfig.getClient());
            advertisementFeignClient.addAdvertisement(advertisement);
        }else{
            advertisementFeignClient.updateAdvertisement(advertisement);
        }
        return "success";
    }
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public void delete(String id){
        logger.info("Advertisement delete id is {}"+id);
        advertisementFeignClient.deleteAdvertisement(id);
    }
}