package com.lexue.sso.service.service;

import com.lexue.base.domain.Schedule;
import com.lexue.base.mybatis.BaseService;
import com.lexue.base.util.DateUtils;
import com.lexue.base.util.ScheduleUtils;
import com.lexue.sso.service.mapper.ScheduleMapper;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 *
 */
@Service
public class ScheduleJobService extends BaseService<ScheduleMapper, Schedule> {
    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void init() throws Exception {
        List<Schedule> scheduleJobList = mapper.findAll(new Schedule());
        for (Schedule scheduleJob : scheduleJobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getId());
            // 如果不存在，则创建
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }
            else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }
    /**
     * 添加定时任务
     *
     * @param
     * @throws ClassNotFoundException
     * @throws SchedulerException
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void addJob(Schedule schedule) throws ClassNotFoundException,
            SchedulerException {
        /*String jobName = schedule.getClassName(),
                jobGroup = schedule.getJobGroup(),
                cronExpression = schedule.getCronExpression(),
                jobDescription = schedule.getDescription(),
                createTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        try {
            *//*if (checkExists(jobName, jobGroup)) {
                logger.info("===> AddJob fail, job already exist, jobGroup:{}, jobName:{}", jobGroup, jobName);
            }*//*
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);

            CronScheduleBuilder schedBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(createTime).withSchedule(schedBuilder).build();
            Class<? extends Job> clazz = (Class<? extends Job>)Class.forName(jobName);
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobKey).withDescription(jobDescription).build();
            scheduler.scheduleJob(jobDetail, trigger);
            schedule.setId(UUID.randomUUID().toString().replace("-",""));
            mapper.addSchedule(schedule);
         }catch (SchedulerException | ClassNotFoundException e) {
            logger.info(e.getMessage());
        }*/
        ScheduleUtils.createScheduleJob(scheduler,schedule);
        mapper.addSchedule(schedule);
    }

    /**
     * 修改定时任务
     * @param info
     * 2016年10月9日下午2:20:07
     */
    public void edit(Schedule info) {
        String jobName = info.getJobName(),
                jobGroup = info.getJobGroup(),
                cronExpression = info.getCronExpression(),
                jobDescription = info.getDescription(),
                createTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        try {
            if (!checkExists(jobName, jobGroup)) {
                logger.info(String.format("Job不存在, jobName:{%s},jobGroup:{%s}", jobName, jobGroup));
            }
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            JobKey jobKey = new JobKey(jobName, jobGroup);
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(createTime).withSchedule(cronScheduleBuilder).build();

            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            jobDetail.getJobBuilder().withDescription(jobDescription);
            HashSet<Trigger> triggerSet = new HashSet<>();
            triggerSet.add(cronTrigger);

            scheduler.scheduleJob(jobDetail, triggerSet, true);
        } catch (SchedulerException e) {
            logger.info(String.format("类名不存在或执行表达式错误"));
        }
    }

    /**
     * 验证是否存在
     * @param jobName
     * @param jobGroup
     * @throws SchedulerException
     * 2016年10月8日下午5:30:43
     */
        private boolean checkExists(String jobName, String jobGroup) throws SchedulerException{
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            //return scheduler.checkExists(triggerKey);
            return true;
        }
    /**
     * 获取所有JobDetail
     *
     * @return 结果集合
     */
    public List<JobDetail> getJobs() {
        try {
            GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            List<JobDetail> jobDetails = new ArrayList<JobDetail>();
            for (JobKey key : jobKeys) {
                jobDetails.add(scheduler.getJobDetail(key));
            }
            return jobDetails;
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取所有计划中的任务
     *
     * @return 结果集合
     */
    public List<Schedule> getAllScheduleJob() {
        List<Schedule> scheduleEntityList = new ArrayList<Schedule>();
        try {
            for(String groupJob: scheduler.getJobGroupNames()){
                for(JobKey jobKey: scheduler.getJobKeys(GroupMatcher.<JobKey>groupEquals(groupJob))){
                    List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                    for (Trigger trigger: triggers) {
                        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                        JobDetail jobDetail = scheduler.getJobDetail(jobKey);

                        String cronExpression = "", createTime = "";

                        if (trigger instanceof CronTrigger) {
                            CronTrigger cronTrigger = (CronTrigger) trigger;
                            cronExpression = cronTrigger.getCronExpression();
                            createTime = cronTrigger.getDescription();
                        }
                        Schedule info = new Schedule();
                        info.setJobName(jobKey.getName());
                        info.setJobGroup(jobKey.getGroup());
                        info.setDescription(jobDetail.getDescription());
                        info.setStatus(triggerState.name());
                        info.setCronExpression(cronExpression);
                        info.setClassName(jobKey.getName());
                        info.setCreateTime(createTime==null?new Date():DateUtils.string2Date(createTime,"yyyy-MM-dd hh:mm:ss"));
                        scheduleEntityList.add(info);
                    }
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return scheduleEntityList;
    }

    /**
     * 获取所有运行中的任务
     *
     * @return 结果集合
     */
    public List<Schedule> getAllRuningScheduleJob() {
        List<Schedule> scheduleEntityList = null;
        try {
            List<JobExecutionContext> executingJobs = scheduler
                    .getCurrentlyExecutingJobs();
            scheduleEntityList = new ArrayList<Schedule>(executingJobs.size());
            for (JobExecutionContext executingJob : executingJobs) {
                Schedule scheduleEntity = new Schedule();
                JobDetail jobDetail = executingJob.getJobDetail();
                JobKey jobKey = jobDetail.getKey();
                Trigger trigger = executingJob.getTrigger();
                scheduleEntity.setJobName(jobKey.getName());
                scheduleEntity.setJobGroup(jobKey.getGroup());
                // scheduleEntity.setDescription("触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler
                        .getTriggerState(trigger.getKey());
                scheduleEntity.setStatus(triggerState.name());
                // 获取要执行的定时任务类名
                scheduleEntity.setClassName(jobDetail.getJobClass().getName());
                // 判断trigger
                if (trigger instanceof SimpleTrigger) {
                    SimpleTrigger simple = (SimpleTrigger) trigger;
                    scheduleEntity.setCronExpression("重复次数:"
                            + (simple.getRepeatCount() == -1 ? "无限" : simple
                            .getRepeatCount()) + ",重复间隔:"
                            + (simple.getRepeatInterval() / 1000L));
                    scheduleEntity.setDescription(simple.getDescription());
                }
                if (trigger instanceof CronTrigger) {
                    CronTrigger cron = (CronTrigger) trigger;
                    scheduleEntity.setCronExpression(cron.getCronExpression());
                    scheduleEntity.setDescription(cron.getDescription());
                }
                scheduleEntityList.add(scheduleEntity);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return scheduleEntityList;
    }

    /**
     * 获取所有的触发器
     *
     * @return 结果集合
     */
    public List<Schedule> getTriggersInfo() {
        try {
            GroupMatcher<TriggerKey> matcher = GroupMatcher.anyTriggerGroup();
            Set<TriggerKey> Keys = scheduler.getTriggerKeys(matcher);
            List<Schedule> triggers = new ArrayList<Schedule>();

            for (TriggerKey key : Keys) {
                Trigger trigger = scheduler.getTrigger(key);
                Schedule scheduleEntity = new Schedule();
                scheduleEntity.setJobName(trigger.getJobKey().getName());
                scheduleEntity.setJobGroup(trigger.getJobKey().getGroup());
                scheduleEntity.setStatus(scheduler.getTriggerState(key) + "");
                if (trigger instanceof SimpleTrigger) {
                    SimpleTrigger simple = (SimpleTrigger) trigger;
                    scheduleEntity.setCronExpression("重复次数:"
                            + (simple.getRepeatCount() == -1 ? "无限" : simple
                            .getRepeatCount()) + ",重复间隔:"
                            + (simple.getRepeatInterval() / 1000L));
                    scheduleEntity.setDescription(simple.getDescription());
                }
                if (trigger instanceof CronTrigger) {
                    CronTrigger cron = (CronTrigger) trigger;
                    scheduleEntity.setCronExpression(cron.getCronExpression());
                    scheduleEntity.setDescription(cron.getDescription());
                }
                triggers.add(scheduleEntity);
            }
            return triggers;
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 暂停任务
     *
     * @throws SchedulerException
     */
    public void stopJob(String id) throws SchedulerException {
        try {
            ScheduleUtils.pauseJob(scheduler,id);
            mapper.updateStatus(id,"1");
            logger.info("====> Pause success jobId:{}"+id);
        }catch (Exception e){
            logger.info("====> Pause error jobId:{}"+id);
        }

    }

    /**
     * 恢复任务
     *
     * @throws SchedulerException
     */
    public void restartJob(String id) throws SchedulerException {
        try {
            ScheduleUtils.resumeJob(scheduler,id);
            mapper.updateStatus(id,"0");
            logger.info("====> Resume success jobId:{}"+id);
        }catch (Exception e){
            logger.info("====> Resume error jobId:{}"+id);
        }

    }

    /**
     * 立马执行一次任务
     *
     * @throws SchedulerException
     */
    public void startNowJob(String id) throws SchedulerException {
        Schedule schedule=mapper.getScheduleById(id);
        String name=schedule.getClassName(),
                group=schedule.getJobGroup();
        JobKey jobKey = JobKey.jobKey(name, group);
        scheduler.triggerJob(jobKey);
    }

    /**
     * 删除任务
     *
     * @throws SchedulerException
     */
    public void delJob(String id) throws SchedulerException {
        try {
            ScheduleUtils.deleteScheduleJob(scheduler,id);
            mapper.deleteSchedule(id);
            logger.info("====> delete success jobId:{}"+id);
        }catch (Exception e){
            logger.info("====> delete error jobId:{}"+id);
        }

    }

    /**
     * 修改触发器时间
     *
     * @throws SchedulerException
     */
    public void modifyTrigger(String id) throws SchedulerException {
        Schedule schedule=mapper.getScheduleById(id);
        String name=schedule.getClassName(),
                cron=schedule.getCronExpression(),
                group=schedule.getJobGroup();
        TriggerKey key = TriggerKey.triggerKey(name, group);
        // Trigger trigger = scheduler.getTrigger(key);


        CronTrigger newTrigger = (CronTrigger) TriggerBuilder.newTrigger()
                .withIdentity(key)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .build();
        scheduler.rescheduleJob(key, newTrigger);
    }

    /**
     * 暂停调度器
     *
     * @throws SchedulerException
     */
    public void stopScheduler() throws SchedulerException {
        if (!scheduler.isInStandbyMode()) {
            scheduler.standby();
        }
    }

    public List<Schedule> findPage(int pageIndex,int pageSize) {
        List<Schedule> role = mapper.findPage((pageIndex-1)*pageSize,pageSize);
        List<Schedule> list=getAllScheduleJob();
        for(Schedule schedule:role){
            for(Schedule sch:list){
                if(schedule.getClassName().equals(sch.getClassName())){
                    schedule.setStatus(sch.getStatus());
                }
            }

        }
        return role;
    }
    public int findPageCount(){
        return mapper.findPageCount();
    }
}
