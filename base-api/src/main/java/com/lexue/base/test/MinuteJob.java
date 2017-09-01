package com.lexue.base.test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 该方法仅仅用来测试每分钟执行
 * @author lance
 */
public class MinuteJob implements Job {
	private static final Logger logger = LoggerFactory.getLogger(MinuteJob.class);


	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("JobName: {}", context.getJobDetail().getKey().getName());
		logger.info("JobGroup: {}", context.getJobDetail().getKey().getGroup());
	}
}