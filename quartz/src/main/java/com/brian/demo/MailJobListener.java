package com.brian.demo;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class MailJobListener implements JobListener {

    public String getName() {
        return "MailJobListener";
    }

    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        System.out.println("准备执行："+ jobExecutionContext.getJobDetail().getKey());
    }

    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        System.out.println("取消执行："+ jobExecutionContext.getJobDetail().getKey());
    }

    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        System.out.println("执行结果："+ jobExecutionContext.getJobDetail().getKey());
    }
}
