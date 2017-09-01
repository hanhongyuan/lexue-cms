package com.lexue.base.domain;

import com.lexue.base.annotation.mybatis.MyColumn;
import com.lexue.base.annotation.mybatis.MyId;
import com.lexue.base.annotation.mybatis.MyTable;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务实体
 * @author yang
 */
@Data
@MyTable("schedule_job")
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";
    @MyId("id")
    private String id;
    @MyColumn("job_name")
    private String jobName; // 任务名
    @MyColumn("job_group")
    private String jobGroup; // 任务组
    @MyColumn("params")
    private String params; // 任务组
    @MyColumn("cron_expression")
    private String cronExpression; // cron表达式
    @MyColumn("status")
    private String status; // 状态
    @MyColumn("description")
    private String description; // 描述
    @MyColumn("class_name")
    private String className; // 执行任务的类(完整路径  包含包名)
    @MyColumn("method_name")
    private String methodName;//执行任务的方法名
    @MyColumn("create_time")
    private Date createTime;

    @Override
    public String toString() {
        return "Schedule{" +
                "id='" + id + '\'' +
                ", jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}