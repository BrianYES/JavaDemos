package com.brian.demo;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class TestCronTrigger {

    public static void main(String[] args) throws Exception {
        test();
    }

    public static void test() throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail jobDetail = JobBuilder.newJob(MailJob.class).build();

        // 每隔2秒执行一次
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/2 * * * * ?");
        CronTrigger trigger = TriggerBuilder.newTrigger().withSchedule(cronScheduleBuilder).build();

        scheduler.scheduleJob(jobDetail, trigger);

        System.out.println("使用的Cron表达式是：" + trigger.getCronExpression());

        scheduler.start();

        Thread.sleep(200000);
        scheduler.shutdown(true);
    }
}
