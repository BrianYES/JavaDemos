package com.brian.demo;

import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

/**
 * 必须实现InterruptableJob 才能够被中断。 Job不行
 *
 * @author Brian
 * @date 2018/5/15
 */
public class StopJob implements InterruptableJob {

    private boolean stop = false;

    public void interrupt() throws UnableToInterruptJobException {
        System.out.println("被调度叫停");
        stop = true;
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        while (true) {
            if (stop) {break;}

            try {
                System.out.println("每隔一秒，进行一次检查是否停止");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("持续工作中...");
        }
    }
}
