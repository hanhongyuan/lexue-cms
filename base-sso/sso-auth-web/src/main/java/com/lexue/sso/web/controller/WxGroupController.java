package com.lexue.sso.web.controller;

import com.alibaba.fastjson.JSON;
import com.lexue.base.annotation.SystemControllerLog;
import com.lexue.base.annotation.ssoauth.Permission;
import com.lexue.base.domain.Group;
import com.lexue.base.domain.WxGroup;
import com.lexue.base.model.GroupModel;
import com.lexue.base.model.WxModel;
import com.lexue.base.util.Base64Utils;
import com.lexue.base.util.ResponseResult;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.web.feignclient.WxGroupFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by lilong on 17-8-3.
 */
@Controller
@RequestMapping("/wx")
public class WxGroupController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    WxGroupFeignClient wxGroupFeignClient;

    @Value("${wx.wxGroup.url}")
    private String wxGroupUrl;
    @Value("${wx.group.url}")
    private String groupUrl;

    @SystemControllerLog(module = "微信群组管理",method = "微信群列表")
    @Permission("sys:wx:list")
    @RequestMapping(value = {"/list","/"},method = RequestMethod.GET)
    public String index(){
        return "weixin/list";
    }
    @RequestMapping(value = "/data",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<WxGroup> data(@RequestParam(value = "pageIndex",defaultValue ="1")Integer pageIndex,
                                        @RequestParam(value = "pageSize",defaultValue = "15")Integer pageSize){
        logger.info("WxGroupController  WxGroup   init   data");
        ResponseResult<WxGroup> data=new ResponseResult<WxGroup>();
        List<WxGroup> list=wxGroupFeignClient.findPage(pageIndex,pageSize).getData();
        data.setList(list);
        data.setRel(true);
        data.setCount(wxGroupFeignClient.findPageCount().getData());
        logger.info("data"+data.toString());
        return data;
    }

    @Permission("sys:wx:add")
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(String id,Model model){
        ResultData<List<Group>> list=wxGroupFeignClient.findGroupList();
        if(list.getCode()==200&&list.getData().size()>0){
            model.addAttribute("groupList",list.getData());
        }else
            model.addAttribute("groupList",null);

        if (id!=null){
            model.addAttribute("wxGroup",wxGroupFeignClient.getWxGroup(id).getData());
        }else
            model.addAttribute("wxGroup",new WxGroup());
        return "weixin/add";
    }
    @SystemControllerLog(module = "微信群组管理",method = "新增修改微信群")
    @Permission("sys:wx:save")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResultData save(WxGroup wxGroup){
        logger.info("WxGroupController save WxGroup data is {}"+wxGroup.toString());
        Group group=wxGroupFeignClient.getGroup(wxGroup.getGroupId()).getData();
        wxGroup.setGid(group.getGid());
        if(wxGroup.getId()==null||"".equals(wxGroup.getId())){
            wxGroup.setId(UUID.randomUUID().toString().replace("-",""));
            wxGroup.setCreateDate(new Date());
            wxGroup.setUpdateDate(new Date());
            wxGroup.setDelFlag("0");
            logger.info("wxGroup  url  is {}"+wxGroupUrl);
            logger.info( "Do Post WxGroup Response is {}"+wxGroupToModel(wxGroup));
            return wxGroupFeignClient.addWxGroup(wxGroup);
        }else {
            WxGroup wx=wxGroupFeignClient.getWxGroup(wxGroup.getId()).getData();
            if(wxGroup.getQrCodePath()==null||"".equals(wxGroup.getQrCodePath())){
                wxGroup.setQrCodePath(wx.getQrCodePath());
            }
            wxGroup.setUpdateDate(new Date());
            logger.info("wxGroup  url  is {}"+wxGroupUrl);
            logger.info("Do Post WxGroup Response is {}"+ wxGroupToModel(wxGroup));
            return wxGroupFeignClient.updateWxGroup(wxGroup);
        }

    }
    @Permission("sys:wx:delete")
    @SystemControllerLog(module = "微信群组管理",method = "删除微信群")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public void delete(String id){
        logger.info("WxGroupController delete WxGroup id is {}"+id);
        wxGroupFeignClient.deleteWxGroup(id);
    }

    @SystemControllerLog(module = "微信群组管理",method = "微信组列表")
    @Permission("sys:group:list")
    @RequestMapping(value = {"/group","/list2"},method = RequestMethod.GET)
    public String index2(){
        return "weixin/group_list";
    }

    @RequestMapping(value = "/GroupData",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Group> data2(@RequestParam(value = "pageIndex",defaultValue ="1")Integer pageIndex,
                                      @RequestParam(value = "pageSize",defaultValue = "15")Integer pageSize){
        logger.info("WxGroupController  Group   init   data");
        ResponseResult<Group> data=new ResponseResult<Group>();
        List<Group> list=wxGroupFeignClient.findPageGroup(pageIndex,pageSize).getData();
        data.setList(list);
        data.setRel(true);
        data.setCount(wxGroupFeignClient.findPageGroupCount().getData());
        logger.info("data"+data.toString());
        return data;
    }

    @Permission("sys:group:add")
    @RequestMapping(value = "/addG",method = RequestMethod.GET)
    public String addG(String id,Model model){
        if (id!=null){
            model.addAttribute("group",wxGroupFeignClient.getGroup(id).getData());
        }else{
            model.addAttribute("group",new Group());
        }
        return "weixin/add_group";
    }
    @SystemControllerLog(module = "微信群组管理",method = "新增修改微信组")
    @Permission("sys:group:save")
    @RequestMapping(value = "/saveG",method = RequestMethod.POST)
    public ResultData saveG(Group group){
        logger.info("WxGroupController save Group data is {}"+group.toString());
        if(group.getId()==null||"".equals(group.getId())){
            group.setId(UUID.randomUUID().toString().replace("-",""));
            group.setCreateDate(new Date());
            group.setUpdateDate(new Date());
            group.setDelFlag("0");
            logger.info("group  url  is {}"+groupUrl);
            logger.info("Do Post Group Response is {}"+groupToModel(group,"INSERT"));
            return wxGroupFeignClient.addGroup(group);
        }else {
            Group g=wxGroupFeignClient.getGroup(group.getId()).getData();
            if(g.getBackgroundImage()==null||"".equals(g.getBackgroundImage())){
                group.setBackgroundImage(g.getBackgroundImage());
            }
            group.setUpdateDate(new Date());
            logger.info("group  url  is {}"+groupUrl);
            logger.info("Do Post Group Response is {}"+groupToModel(group,"UPDATE"));
            return wxGroupFeignClient.updateGroup(group);
        }
    }
    @Permission("sys:group:delete")
    @SystemControllerLog(module = "微信群组管理",method = "删除微信组")
    @RequestMapping(value = "/deleteG",method = RequestMethod.POST)
    public void deleteG(String id){
        logger.info("WxGroupController delete Group id is {}"+id);
        wxGroupFeignClient.deleteGroup(id);
    }

    @RequestMapping("/upload")
    @ResponseBody
    public ResultData<String> uploadQcCode(@RequestParam("file") MultipartFile file) {
        String str= ClassUtils.getDefaultClassLoader().getResource("").getPath();
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            String trueFileName = String.valueOf(System.currentTimeMillis()) + "." + type.toLowerCase();
            String path="/upload/"+trueFileName;
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(str+path)));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                logger.info("Upload Pictures FileNotFoundException error");
                return ResultUtil.error();
            } catch (IOException e) {
                logger.info("Upload Pictures IOException error");
                return ResultUtil.error();
            }
            return ResultUtil.success(path,fileName);
        } else {
            logger.info("Upload Pictures isEmpty error");
            return ResultUtil.error();
        }
    }
    public String getImageBase64Url(String path,String model){
        String str= ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String type = path.substring(path.lastIndexOf(".") + 1, path.length());
        try {
            FileInputStream fs=new FileInputStream(new File(str+path));
            if(model.equals("wxGroup")){
                    return "data:image/" + type + ";base64,"+Base64Utils.GetImageStrByInPut(fs);
            }else {
                return Base64Utils.GetImageStrByInPut(fs);
            }
        }catch (Exception e){
            logger.info("Picture transfer to Base64 error");
        }
        return null;
    }
    public String wxGroupToModel(WxGroup wxGroup){
        WxModel wxModel=new WxModel();
        wxModel.setWid(wxGroup.getWid());
        wxModel.setGid(wxGroup.getGid());
        wxModel.setSize(wxGroup.getGroupNum());
        wxModel.setGrpn(wxGroup.getName());
        wxModel.setIuri(getImageBase64Url(wxGroup.getQrCodePath(),"wxGroup"));
        logger.info("JSON String is "+JSON.toJSONString(wxModel));
        return JSON.toJSONString(wxModel);
    }
    public String groupToModel(Group group,String oper){
        GroupModel groupModel=new GroupModel();
        groupModel.setGroup("wechat");
        groupModel.setName(group.getBackgroundImage().replace("/upload/",""));
        groupModel.setOper(oper);
        groupModel.setData(getImageBase64Url(group.getBackgroundImage(),"group"));
        logger.info("JSON String is "+JSON.toJSONString(groupModel));
        return JSON.toJSONString(groupModel);
    }
}
