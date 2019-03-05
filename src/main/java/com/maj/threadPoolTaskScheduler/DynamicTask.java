package com.maj.threadPoolTaskScheduler;

import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DynamicTask{
    
    private String cron;
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    
    @SuppressWarnings("rawtypes")
    private ScheduledFuture future;
    
    public void startCron(){
        cron = "0/1 * * * * ?";
        System.out.println(Thread.currentThread().getName());
        String name = Thread.currentThread().getName();
        future = threadPoolTaskScheduler.schedule(new myTask(name), new CronTrigger(cron));
        ThreadPoolTaskSchedulerApplication.map.put(name, future);
    }
    
    public void stop(){
        if(future != null){
            future.cancel(true);
        }
    }
    
    private class myTask implements Runnable{
        
        private String name;
        
        myTask(String name){
            this.name = name;
        }
        
        @Override
        public void run(){
            System.out.println("test" + name);
        }
    }
    
}
