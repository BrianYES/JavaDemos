package com.brian.demo;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuertzTest {

    public static void main(String[] args) throws Exception {
        stopJob();
    }

    private static void stopJob() throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger", "group").startNow().build();

        JobDetail job = JobBuilder.newJob(StopJob.class).withIdentity("stopJob", "stopGroup").build();

        scheduler.scheduleJob(job, trigger);

        scheduler.start();

        // 过5秒 停止Job
        Thread.sleep(5000);
        System.out.println("过5秒，调度停止Job");
        scheduler.interrupt(job.getKey());

        Thread.sleep(20000);
        scheduler.shutdown(true);
    }

    private static void exceptionJob() throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        SimpleScheduleBuilder simpleSchedule = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).withRepeatCount(2);
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger", "group").startNow().withSchedule(simpleSchedule).build();

        JobDetail job = JobBuilder.newJob(ExceptionJob.class).withIdentity("exceptionJob", "exceptionGroup").build();

        scheduler.scheduleJob(job, trigger);

        scheduler.start();

        Thread.sleep(7000);
        scheduler.shutdown(true);
    }

    private static void databaseBackupJob() throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        SimpleScheduleBuilder simpleSchedule = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2)
                .withRepeatCount(2);
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startNow().withSchedule(simpleSchedule).build();

        JobDetail job = JobBuilder.newJob(DatabaseBackupJob.class).withIdentity("databaseBackupJob", "databaseBackupGroup")
                .usingJobData
                ("database",
                "test").build();

        scheduler.scheduleJob(job, trigger);

        scheduler.start();

        Thread.sleep(40000);
        scheduler.shutdown(true);
    }

    private static void mailJob() throws Exception {
        // 创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        SimpleScheduleBuilder simpleSchedule = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).withRepeatCount(10);
        // 定义一个触发器
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startNow().withSchedule(simpleSchedule).build();

        // 定义一个JobDetail
        JobDetail job = JobBuilder.newJob(MailJob.class).withIdentity("mailJob1", "mailgroup").usingJobData("email",
                "admin@10086.com").build();
        job.getJobDataMap().put("email", "admin@brian.com");

        // 调度加入job
        scheduler.scheduleJob(job, trigger);

        // 启动
        scheduler.start();

        // 等待20秒，让前面的任务都执行完了之后，再关闭调度器
        Thread.sleep(20000);
        scheduler.shutdown(true);
    }
}
