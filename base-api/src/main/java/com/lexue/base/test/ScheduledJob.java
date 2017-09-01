    package com.lexue.base.test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

    /**
     * @Copyright: Copyright (c) 2016,${year},
     * @Description: ${todo}
     */
    @Component("scheduledJob")
    public class ScheduledJob implements Job {
        private static final Logger logger = LoggerFactory.getLogger(ScheduledJob.class);

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            logger.info("JobName: {}", jobExecutionContext.getJobDetail().getKey().getName());
            logger.info("JobGroup: {}", jobExecutionContext.getJobDetail().getKey().getGroup());
        }
    }
