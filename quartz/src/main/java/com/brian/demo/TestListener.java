package com.brian.demo;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

public class TestListener {

    public static void main(String[] args) throws Exception {
        testJobListener();
    }

    private static void testJobListener() throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail jobDetail = JobBuilder.newJob(MailJob.class).build();
        Trigger trigger = TriggerBuilder.newTrigger().startNow().build();

        MailJobListener listener = new MailJobListener();
        KeyMatcher<JobKey> keyMatcher = KeyMatcher.keyEquals(jobDetail.getKey());
        scheduler.getListenerManager().addJobListener(listener, keyMatcher);

        scheduler.scheduleJob(jobDetail, trigger);

        scheduler.start();

        Thread.sleep(20000);
        scheduler.shutdown(true);
    }
}
