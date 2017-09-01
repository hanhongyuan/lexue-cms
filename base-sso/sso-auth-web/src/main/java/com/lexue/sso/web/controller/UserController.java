package com.lexue.sso.web.controller;

import com.lexue.base.annotation.SystemControllerLog;
import com.lexue.base.annotation.ssoauth.Permission;
import com.lexue.base.domain.User;
import com.lexue.base.util.*;
import com.lexue.sso.web.feignclient.RoleFeignClient;
import com.lexue.sso.web.feignclient.UserFeignClient;
import com.lexue.sso.web.service.SsoUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by lilong on 17-7-20.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserFeignClient userFeignClient;
    @Autowired
    RoleFeignClient roleFeignClient;
    @Autowired
    private SsoUserService ssoUserService;

    @SystemControllerLog(module = "用户管理",method = "用户列表")
    @Permission("sys:user:list")
    @RequestMapping(value = {"/","/list"},method = RequestMethod.GET)
    public String index(){
        return "user/list";
    }
    @RequestMapping(value = "/data",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<User> data(
                                     @RequestParam(value = "pageIndex",defaultValue ="1")Integer pageIndex,
                                     @RequestParam(value = "pageSize",defaultValue = "15")Integer pageSize){
        logger.info("UserController.data   init   data");
        List<User> list=userFeignClient.getAllUser().getData();
        ResponseResult<User> data=new ResponseResult<User>();
        data.setMsg("");
        data.setCount(list.size());
        data.setRel(true);
        logger.info(list.size()/pageSize+1+"");
        if(pageIndex==(list.size()/pageSize+1)){
            data.setList(list.subList((pageIndex-1)*pageSize,list.size()));
        }else{
            data.setList(list.subList((pageIndex-1)*pageSize,(pageIndex-1)*pageSize+pageSize));
        }
        logger.info("data"+data.toString());
        return data;
    }
    @Permission("sys:user:add")
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(String id, Model model){
        model.addAttribute("roles",roleFeignClient.getAllRole().getData());
        if(id!=null){
            model.addAttribute("user",userFeignClient.getUser(id).getData());
        }else
            model.addAttribute("user",new User());
        return "user/add";
    }
    @Permission("sys:user:save")
    @SystemControllerLog(module = "用户管理",method = "新增修改用户")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public ResultData save(User user,@RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex){
        logger.info("UserController user add  save start  ");
            if(user.getId()==null||"".equals(user.getId())){
                user.setId(UUID.randomUUID().toString().replace("-",""));
                if(user.getPassword()!=null&&!"".equals(user.getPassword())){
                    user.setPassword(PasswordUtil.entryptPassword(user.getPassword()));
                }
                user.setName(user.getName());
                user.setCreateBy("1");
                user.setUpdateDate(new Date());
                user.setDelFlag("0");
                user.setLoginFlag(user.getLoginFlag());
                userFeignClient.addUser(user);
            }else {
                User oldUser=userFeignClient.getUser(user.getId()).getData();
                user.setPassword(oldUser.getPassword());
                userFeignClient.updateUser(user);
            }
            logger.info("UserController user add  save end  ");
            return ResultUtil.success(pageIndex);
    }
    @Permission("sys:user:delete")
    @SystemControllerLog(module = "用户管理",method = "删除用户")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public void delete(String id){
        logger.info("UserController user delete id is {}"+id);
        userFeignClient.deleteUser(id);
    }
    @RequestMapping(value = "/getRids",method = RequestMethod.POST)
    @ResponseBody
    public List<String> getRids(String userId){
        logger.info("UserController user getRids id is {}"+userId);
        return  userFeignClient.getRids(userId).getData();
    }
    @RequestMapping(value = "/showUser", method = RequestMethod.GET)
    public String showUser(Model model, HttpServletRequest request) {
        User user=ssoUserService.getUser();
        model.addAttribute("user",user);
        return "user/userInfo";
    }
    @RequestMapping(value = "/updatePwd", method = RequestMethod.GET)
    public String updatePwd(Model model, HttpServletRequest request){
        return "user/userPwd";
    }
    @RequestMapping(value = "/validationPwd", method = RequestMethod.GET)
    @ResponseBody
    public ResultData<Object> validationPwd(String pwd, HttpServletRequest request){
       User user=ssoUserService.getUser();
       if(user!=null&&PasswordUtil.validatePassword(pwd,user.getPassword())){
           return ResultUtil.success(true);
       }
       return ResultUtil.success(false);
    }
    @RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
    @ResponseBody
    public ResultData<Object> updatePassword(String newPassword, HttpServletRequest request){
        User user=ssoUserService.getUser();
        user.setPassword(PasswordUtil.entryptPassword(newPassword));
        userFeignClient.updateUser(user);
        return ResultUtil.success();
    }

    @RequestMapping(value = "checkLoginName",method = RequestMethod.GET)
    @ResponseBody
    public ResultData<Object> checkLoginName(String loginName,String id){
       return userFeignClient.checkLoginName(loginName,id);
    }
}
