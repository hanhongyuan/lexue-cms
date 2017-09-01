package com.lexue.sso.web.controller;

import com.lexue.base.annotation.ssoauth.Permission;
import com.lexue.base.domain.Dict;
import com.lexue.base.domain.Log;
import com.lexue.base.util.ResponseResult;
import com.lexue.sso.web.feignclient.LogFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lilong on 17-7-25.
 */
@Controller
@RequestMapping("/log")
public class LogController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    LogFeignClient logFeignClient;
    @Permission("sys:log:list")
    @RequestMapping(value = {"","/list","/"},method = RequestMethod.GET)
    public String index(){
        return "log/list";
    }
    @RequestMapping(value = "/data",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Log> data(@RequestParam(value = "pageIndex",defaultValue ="1")Integer pageIndex,
                                    @RequestParam(value = "pageSize",defaultValue = "15")Integer pageSize){
        ResponseResult<Log> data=new ResponseResult<Log>();
        logger.info("LogController init PageData");
        List<Log> list=logFeignClient.findPage(pageIndex,pageSize).getData();
        data.setList(list);
        data.setRel(true);
        data.setCount(logFeignClient.findPageCount().getData());
        logger.info("data"+data.toString());
        return data;
    }
}
