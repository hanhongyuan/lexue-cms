package com.lexue.sso.service.resource;

import com.lexue.base.domain.Log;
import com.lexue.base.domain.Schedule;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.service.service.ScheduleJobService;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lilong on 17-7-27.
 */
@RestController
@RequestMapping("/job")
public class JobResource {

    @Autowired
    private ScheduleJobService jobService;

    /**
     * 添加定时任务
     *
     * @param
     */
    @PostMapping("")
    public ResultData addJob(@RequestBody(required = true) Schedule scheduleEntity) throws ClassNotFoundException,
            SchedulerException{
        jobService.addJob(scheduleEntity);
        return ResultUtil.success();
    };

    /**
     * 获取所有JobDetail
     *
     * @return 结果集合
     */
    @GetMapping("/getJobs")
    public ResultData<List<JobDetail>> getJobs(){
       return ResultUtil.success(jobService.getJobs());
    };

    /**
     * 获取所有计划中的任务
     *
     * @return 结果集合
     */
    @GetMapping()
    public ResultData<List<Schedule>> getAllScheduleJob(){
       return ResultUtil.success(jobService.getAllScheduleJob());
    };

    /**
     * 获取所有运行中的任务
     *
     * @return 结果集合
     */
    public ResultData<List<Schedule>> getAllRuningScheduleJob(){
        return ResultUtil.success(jobService.getAllRuningScheduleJob());
    };

    /**
     * 获取所有的触发器
     *
     * @return 结果集合
     */
    @GetMapping("/getTriggersInfo")
    public ResultData<List<Schedule>> getTriggersInfo(){
        return ResultUtil.success(jobService.getTriggersInfo());
    };

    /**
     * 暂停任务
     *
     */
    @GetMapping("/stopJob")
    public ResultData stopJob(@RequestHeader("id") String id) throws SchedulerException{
        jobService.stopJob(id);
        return ResultUtil.success();
    }

    /**
     * 恢复任务
     *
     */
    @GetMapping("/restartJob")
    public ResultData restartJob(@RequestHeader("id")String id) throws SchedulerException{
        jobService.restartJob(id);
        return ResultUtil.success();
    }

    /**
     * 立马执行一次任务
     *
     */
    @GetMapping("/startNowJob")
    public ResultData startNowJob(@RequestHeader("id")String id)
            throws SchedulerException{
        jobService.startNowJob(id);
        return ResultUtil.success();
    }

    /**
     * 删除任务
     *
     */
    @GetMapping("/delJob")
    public ResultData delJob(@RequestHeader("id")String id) throws SchedulerException{
        jobService.delJob(id);
        return ResultUtil.success();
    }

    /**
     * 修改触发器时间
     *
     * @param name  任务名
     * @param group 任务组
     * @param cron  cron表达式
     */
    @GetMapping("/modifyTrigger")
    public ResultData modifyTrigger(@RequestHeader("name")String name, @RequestHeader("group")String group, @RequestHeader("cron")String cron) throws SchedulerException{
       // jobService.modifyTrigger(name,group,cron);
        return ResultUtil.success();
    }

    /**
     * 暂停调度器
     */
    public ResultData stopScheduler() throws SchedulerException{
        jobService.stopScheduler();
        return ResultUtil.success();
    }
    @GetMapping("/findPage")
    public ResultData<List<Schedule>> findPage(@RequestHeader("pageIndex") int pageIndex, @RequestHeader("pageSize") int pageSize) {
        List<Schedule> role = jobService.findPage(pageIndex,pageSize);
        return ResultUtil.success(role);
    }
    @GetMapping("/findPageCount")
    public ResultData findPageCount(){
        return ResultUtil.success(jobService.findPageCount());
    }
}
