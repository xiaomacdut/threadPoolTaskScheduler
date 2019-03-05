package com.maj.threadPoolTaskScheduler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
public class ThreadPoolTaskSchedulerApplication {

    @SuppressWarnings("rawtypes")
    public static ConcurrentHashMap<String, ScheduledFuture> map = new ConcurrentHashMap<String, ScheduledFuture>();
    
    
	public static void main(String[] args) {
		SpringApplication.run(ThreadPoolTaskSchedulerApplication.class, args);
	}

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(20);
        scheduler.setThreadNamePrefix("taskExecutor-");
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        scheduler.setAwaitTerminationSeconds(60);
        return scheduler;
    }
}
