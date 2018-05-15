package com.brian.demo;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class TestSimpleTrigger {

    public static void main(String[] args) throws Exception {
        test1();
    }

    public static void test1() throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail jobDetail = JobBuilder.newJob(MailJob.class).build();

        // 秒钟是15的倍数 例如当前时间是08:00:02  那么运行时间就是08:00:15分钟运行
//        Date startTime = DateBuilder.nextGivenSecondDate(null, 15);

        // 10 秒后运行
        Date startTime = DateBuilder.futureDate(10, DateBuilder.IntervalUnit.SECOND);

//        累计4次，间隔2秒
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withRepeatCount(3)
                .withIntervalInSeconds(2);

        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                .startAt(startTime)
                .withSchedule(simpleScheduleBuilder)
                .build();

        Date ft = scheduler.scheduleJob(jobDetail, trigger);

        System.out.println("当前时间是：" + new Date().toLocaleString());
        System.out.printf("%s 这个任务会在 %s 准时开始运行，累计运行%d次，间隔时间是%d毫秒%n", jobDetail.getKey(), ft.toLocaleString(), trigger
                .getRepeatCount()+1, trigger.getRepeatInterval());

        scheduler.start();

        Thread.sleep(200000);
        scheduler.shutdown(true);
    }
}
