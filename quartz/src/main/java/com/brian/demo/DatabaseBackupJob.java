package com.brian.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

// 注解：一定要等待上次任务完成之后才能开始！即便是规定时间到了，也不能开始！
@DisallowConcurrentExecution
public class DatabaseBackupJob implements Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail detail = jobExecutionContext.getJobDetail();
        String database = detail.getJobDataMap().getString("database");

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String now = sdf.format(new Date());

        System.out.printf("给数据库 %s 备份, 耗时10秒 当前时间：%s %n" ,database, now);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
