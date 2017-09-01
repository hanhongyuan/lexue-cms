package com.lexue.sso.web.feignclient;

import com.lexue.base.domain.Schedule;
import com.lexue.base.util.ResultData;
import com.lexue.sso.web.feignclient.fallback.ScheduleJobFeignHystrix;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * Created by lilong on 17-7-27.
 */
@FeignClient(name = "${spring.sso-auth-name}",fallback = ScheduleJobFeignHystrix.class)
public interface ScheduleJobFeignClient {

    /**
     * 添加定时任务
     *
     * @param
     */
    @PostMapping("/job")
    public ResultData addJob(@RequestBody(required = true) Schedule scheduleEntity) throws ClassNotFoundException,
            SchedulerException;

    /**
     * 获取所有JobDetail
     *
     * @return 结果集合
     */
    @GetMapping("/job/getJobs")
    public ResultData<List<JobDetail>> getJobs();

    /**
     * 获取所有计划中的任务
     *
     * @return 结果集合
     */
    @GetMapping("/job")
    public ResultData<List<Schedule>> getAllScheduleJob();

    /**
     * 获取所有运行中的任务
     *
     * @return 结果集合
     */
    @GetMapping("/job/getAllRuningScheduleJob")
    public ResultData<List<Schedule>> getAllRuningScheduleJob();

    /**
     * 获取所有的触发器
     *
     * @return 结果集合
     */
    @GetMapping("/job/getTriggersInfo")
    public ResultData<List<Schedule>> getTriggersInfo();

    /**
     * 暂停任务
     *
     * @param id
     */
    @GetMapping("/job/stopJob")
    public ResultData stopJob(@RequestHeader("id") String id) throws SchedulerException;

    /**
     * 恢复任务
     *
     */
    @GetMapping("/job/restartJob")
    public ResultData restartJob(@RequestHeader("id")String id) throws SchedulerException;

    /**
     * 立马执行一次任务
     *
     */
    @GetMapping("/job/startNowJob")
    public ResultData startNowJob(@RequestHeader("id")String id)
            throws SchedulerException;

    /**
     * 删除任务
     *
     */
    @GetMapping("/job/delJob")
    public ResultData delJob(@RequestHeader("id")String id) throws SchedulerException;

    /**
     * 修改触发器时间
     *
     */
    @GetMapping("/job/modifyTrigger")
    public ResultData modifyTrigger(@RequestHeader("id")String id) throws SchedulerException;

    /**
     * 暂停调度器
     */
    @GetMapping("/job/stopScheduler")
    public ResultData stopScheduler() throws SchedulerException;

    @GetMapping("/job/findPage")
    ResultData<List<Schedule>> findPage(@RequestHeader("pageIndex") int pageIndex, @RequestHeader("pageSize") int pageSize);

    @GetMapping("/job/findPageCount")
    ResultData<Integer> findPageCount();
}
