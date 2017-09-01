package com.lexue.sso.web.controller;

import com.lexue.base.annotation.SystemControllerLog;
import com.lexue.base.annotation.ssoauth.Permission;
import com.lexue.base.domain.Schedule;
import com.lexue.base.util.CodeEnum;
import com.lexue.base.util.ResponseResult;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.web.feignclient.ScheduleJobFeignClient;
import org.quartz.CronExpression;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by yang on 2016/5/31 0031.
 */
@Controller
@RequestMapping("/job")
public class ScheduleJobController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    ScheduleJobFeignClient scheduleJobFeignClient;

    /**
     * 任务列表
     *
     * @return
     */
    @Permission("sys:job:list")
    @SystemControllerLog(module = "定时任务",method = "定时任务列表")
    @RequestMapping("/list")
    public String list() {
        return "job/list";
    }

    @RequestMapping(value = "/data",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Schedule> data(@RequestParam(value = "pageIndex",defaultValue ="1")Integer pageIndex,
                                         @RequestParam(value = "pageSize",defaultValue = "15")Integer pageSize){
        ResponseResult<Schedule> data=new ResponseResult<Schedule>();
        logger.info("RoleController init PageData");
        List<Schedule> list=scheduleJobFeignClient.findPage(pageIndex,pageSize).getData();
        data.setList(list);
        data.setRel(true);
        data.setCount(scheduleJobFeignClient.findPageCount().getData());
        logger.info("data"+data.toString());
        return data;
    }
    @Permission("sys:job:add")
    @RequestMapping("add")
    public String add() {
        return "job/add";
    }
    @Permission("sys:job:save")
    @SystemControllerLog(module = "定时任务",method = "新增定时任务")
    @ResponseBody
    @RequestMapping("/save")
    public ResultData create(Schedule scheduleEntity) {
        // 判断表达式
        boolean f = CronExpression.isValidExpression(scheduleEntity
                .getCronExpression());
        if (!f) {
            return ResultUtil.error(CodeEnum.CRON_ERROR);
        }
        try {
            scheduleEntity.setId(UUID.randomUUID().toString().replace("-",""));
            scheduleEntity.setStatus("0");
            scheduleJobFeignClient.addJob(scheduleEntity);
            return ResultUtil.success();
        } catch (ClassNotFoundException e) {
           logger.error(e.getMessage());
            return ResultUtil.error(CodeEnum.ERROR_CLASS_NOT_FOUNT);
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResultUtil.error(CodeEnum.ERROR_NOT_CAUSE);
        } catch (Exception e) {
            return ResultUtil.error(CodeEnum.ERROR);
        }
    }

    /**
     * 暂停任务
     */
    @SystemControllerLog(module = "定时任务",method = "暂停定时任务")
    @ResponseBody
    @Permission("sys:job:stop")
    @RequestMapping("/stopJob")
    public ResultData stop(String id) {
        try {
            scheduleJobFeignClient.stopJob(id);
            return ResultUtil.success("暂停成功");
        } catch (SchedulerException e) {
            logger.error(e.getMessage());
            return ResultUtil.error(CodeEnum.ERROR);
        }
    }
    @SystemControllerLog(module = "定时任务",method = "删除定时任务")
    @ResponseBody
    @Permission("sys:job:delete")
    @RequestMapping("/delete")
    public ResultData delete(String id) {
        try {
            scheduleJobFeignClient.delJob(id);
            return ResultUtil.success();
        } catch (SchedulerException e) {
            logger.error(e.getMessage());
            return ResultUtil.error(CodeEnum.ERROR);
        }
    }

    /**
     * 修改表达式
     */

    @ResponseBody
    @RequestMapping("/update")
    public Object update(Schedule scheduleEntity) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", -1);
        // 验证cron表达式
        boolean f = CronExpression.isValidExpression(scheduleEntity
                .getCronExpression());
        if (!f) {
            map.put("msg", "cron表达式有误，不能被解析！");
            return map;
        }
        /*try {
            scheduleJobFeignClient.modifyTrigger(scheduleEntity.getJobName(),
                    scheduleEntity.getJobGroup(), scheduleEntity.getCronExpression());
            map.put("status", 0);
        } catch (SchedulerException e) {
            e.printStackTrace();
            map.put("msg", "系统错误,请联系管理员!");
        }*/
        return map;
    }

    /**
     * 立即运行一次
     */

    @ResponseBody
    @RequestMapping("/startNow")
    public Object stratNow(String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", -1);
        try {
            scheduleJobFeignClient.startNowJob(id);
            map.put("status", 0);
        } catch (SchedulerException e) {
            e.printStackTrace();
            map.put("msg", "系统错误,请联系管理员!");
        }
        return map;
    }

    /**
     * 恢复
     */
    @SystemControllerLog(module = "定时任务",method = "恢复定时任务")
    @ResponseBody
    @Permission("sys:job:start")
    @RequestMapping("/resume")
    public ResultData resume(String id) {
        try {
            scheduleJobFeignClient.restartJob(id);
            return ResultUtil.success("开始成功");
        } catch (SchedulerException e) {
            logger.error(e.getMessage());
            return ResultUtil.error(CodeEnum.ERROR);
        }
    }

    /**
     * 获取所有trigger
     */
    public void getTriggers(HttpServletRequest request) {
        List<Schedule> scheduleEntities = scheduleJobFeignClient.getTriggersInfo().getData();
        request.setAttribute("triggers", scheduleEntities);
    }
}
