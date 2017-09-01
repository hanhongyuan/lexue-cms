package com.lexue.sso.web.controller;

import com.lexue.base.annotation.SystemControllerLog;
import com.lexue.base.annotation.ssoauth.Permission;
import com.lexue.base.domain.Menu;
import com.lexue.base.domain.Role;
import com.lexue.base.util.ResponseResult;
import com.lexue.sso.web.feignclient.RoleFeignClient;
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
 * Created by lilong on 17-7-24.
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RoleFeignClient roleFeignClient;

    @SystemControllerLog(module = "角色管理",method = "角色列表")
    @Permission("sys:role:list")
    @RequestMapping(value = {"","/list","/"},method = RequestMethod.GET)
    public String index(Model model){
        logger.info("RoleController init data");
        return "role/list";
    }
    @RequestMapping(value = "/data",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Role> data(@RequestParam(value = "pageIndex",defaultValue ="1")Integer pageIndex,
                                     @RequestParam(value = "pageSize",defaultValue = "15")Integer pageSize){
        ResponseResult<Role> data=new ResponseResult<Role>();
        logger.info("RoleController init PageData");
        List<Role> list=roleFeignClient.findPage(pageIndex,pageSize).getData();
        data.setList(list);
        data.setRel(true);
        data.setCount(roleFeignClient.findPageCount().getData());
        logger.info("data"+data.toString());
        return data;
    }
    @Permission("sys:role:add")
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(String id,Model model){
        if (id!=null){
            model.addAttribute("role",roleFeignClient.getRole(id).getData());
            model.addAttribute("menuIds",roleFeignClient.getMenuIdsToRoleId(id).getData());
        }else
            model.addAttribute("role",new Role());
        return "/role/add";
    }
    @Permission("sys:role:save")
    @SystemControllerLog(module = "角色管理",method = "新增修改角色")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public String save(Role role){
        if(role.getId()==null||"".equals(role.getId())){
            role.setId(UUID.randomUUID().toString().replace("-",""));
            role.setUpdateDate(new Date());
            role.setDelFlag("0");
            roleFeignClient.addRole(role);
        }else{
            roleFeignClient.updateRole(role);
        }
        return "success";
    }
    @Permission("sys:role:delete")
    @SystemControllerLog(module = "角色管理",method = "删除角色")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public void delete(String id){
        logger.info("role delete id is {}"+id);
        roleFeignClient.deleteRole(id);
    }
}
