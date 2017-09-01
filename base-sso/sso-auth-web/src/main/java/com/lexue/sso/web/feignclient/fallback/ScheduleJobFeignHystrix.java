package com.lexue.sso.web.feignclient.fallback;

import com.lexue.base.domain.Schedule;
import com.lexue.base.util.CodeEnum;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.web.feignclient.ScheduleJobFeignClient;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lilong on 17-7-31.
 */
@Component
public class ScheduleJobFeignHystrix implements ScheduleJobFeignClient {
    @Override
    public ResultData addJob(Schedule scheduleEntity) throws ClassNotFoundException, SchedulerException {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<List<JobDetail>> getJobs() {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<List<Schedule>> getAllScheduleJob() {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<List<Schedule>> getAllRuningScheduleJob() {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<List<Schedule>> getTriggersInfo() {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData stopJob(String id) throws SchedulerException {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData restartJob(String id) throws SchedulerException {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData startNowJob(String id) throws SchedulerException {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData delJob(String id) throws SchedulerException {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData modifyTrigger(String id) throws SchedulerException {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData stopScheduler() throws SchedulerException {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<List<Schedule>> findPage(int pageIndex, int pageSize) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Integer> findPageCount() {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }
}
