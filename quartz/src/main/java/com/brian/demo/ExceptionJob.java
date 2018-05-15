package com.brian.demo;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 发生异常的Job
 *
 * @author Brian
 * @date 2018/5/15
 */
public class ExceptionJob implements Job {
    static int i = 0;

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            System.out.println("运算结果：" + 100/i);
        } catch (Exception e) {
//            System.out.println("发生异常，取消这个Job所有的调度");
//
//            JobExecutionException je = new JobExecutionException(e);
//            je.setUnscheduleAllTriggers(true);
//            throw je;

            System.out.println("发生异常，修改参数 立即重新执行");
            i = 1;
            JobExecutionException je = new JobExecutionException(e);
            je.setRefireImmediately(true);
            throw je;
        }
    }
}
