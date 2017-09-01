package com.lexue.sso.web.controller;

import com.lexue.base.annotation.SystemControllerLog;
import com.lexue.base.annotation.ssoauth.Permission;
import com.lexue.base.domain.Dict;
import com.lexue.base.domain.Role;
import com.lexue.base.util.ResponseResult;
import com.lexue.sso.web.feignclient.DictFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Created by lilong on 17-7-25.
 */
@Controller
@RequestMapping("/dict")
public class DictController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    DictFeignClient dictFeignClient;
    @Permission("sys:dict:list")
    @SystemControllerLog(module = "字典管理",method = "字典列表")
    @RequestMapping(value = {"","/list","/"},method = RequestMethod.GET)
    public String index(){
        return "dict/list";
    }
    @Permission("sys:dict:add")
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(String id,Model model){
        if(id!=null){
            model.addAttribute("dict",dictFeignClient.getDict(id).getData());
        }else
            model.addAttribute("dict",new Dict());
        return "dict/add";
    }
    @RequestMapping(value = "/addTwo",method = RequestMethod.GET)
    public String addTwo(String id,Model model){
        if(id!=null){
            Dict dict=dictFeignClient.getDict(id).getData();
            dict.setId(null);
            dict.setValue(null);
            dict.setLabel(null);
            dict.setSort(dict.getSort()+10);
            dict.setRemarks(null);
            model.addAttribute("dict",dict);
        }
        return "dict/add";
    }
    @RequestMapping(value = "/data",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Dict> data(@RequestParam(value = "pageIndex",defaultValue ="1")Integer pageIndex,
                                     @RequestParam(value = "pageSize",defaultValue = "15")Integer pageSize){
        ResponseResult<Dict> data=new ResponseResult<Dict>();
        logger.info("DictController init PageData");
        List<Dict> list=dictFeignClient.findPage(pageIndex,pageSize).getData();
        data.setList(list);
        data.setRel(true);
        data.setCount(dictFeignClient.findPageCount().getData());
        logger.info("data"+data.toString());
        return data;
    }
    @Permission("sys:dict:save")
    @SystemControllerLog(module = "字典管理",method = "新增修改字典")
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(Dict dict){
        logger.info("dict save start");
        if(dict.getId()==null||"".equals(dict.getId())){
            dict.setId(UUID.randomUUID().toString().replace("-",""));
            dict.setUpdateDate(new Date());
            dict.setCreateDate(new Date());
            dict.setDelFlag("0");
            dictFeignClient.addDict(dict);
        }else{
            dictFeignClient.updateDict(dict);
        }
        return "success";
    }
    @Permission("sys:dict:delete")
    @SystemControllerLog(module = "字典管理",method = "删除字典")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public void delete(String id){
        logger.info("dict delete id is {}"+id);
        dictFeignClient.deleteDict(id);
    }
}
