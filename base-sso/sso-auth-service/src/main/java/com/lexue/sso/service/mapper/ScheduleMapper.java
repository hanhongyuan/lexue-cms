package com.lexue.sso.service.mapper;

import com.lexue.base.domain.Log;
import com.lexue.base.domain.Schedule;
import com.lexue.base.mybatis.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

/**
 * Created by lilong on 17-7-27.
 */
@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule> {

    @Select("select * from schedule_job  limit ${pageIndex},${pageSize}")
    List<Schedule> findPage(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    @Select("select count(*) from schedule_job")
    public int findPageCount();

    @Select("select * from schedule_job where id='${id}'")
    Schedule getScheduleById(@Param("id")String id);

    @Insert("insert into schedule_job (id,job_group,job_name,class_name,method_name,cron_expression,status,description,create_time,params) " +
            "values ('${id}','${jobName}','${jobGroup}','${className}','${methodName}','${cronExpression}',${status},'${description}',now(),'${params}')")
    @SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id", before = false, resultType = String.class, statementType = StatementType.STATEMENT)
    void addSchedule(Schedule schedule);

    @Delete("delete from schedule_job where id='${id}'")
    void deleteSchedule(@Param("id") String id);

    @Update("update schedule_job set status='${status}' where id='${id}'")
    void updateStatus(@Param("id")String id,@Param("status")String status);
}
