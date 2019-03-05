package com.maj.threadPoolTaskScheduler;

import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController{
    
    @Autowired
    private DynamicTask task;
    
    @RequestMapping("/task")
    public String test() throws Exception{
        // 开启定时任务
        task.startCron();
        return "开启定时任务";
        
    }
    
    @RequestMapping("/stop")
    public void stop() throws Exception{
        // 提前测试用来测试线程1进行对比是否关闭。
        @SuppressWarnings("rawtypes")
        ScheduledFuture scheduledFuture = ThreadPoolTaskSchedulerApplication.map.get("http-nio-8080-exec-2");
        scheduledFuture.cancel(true);
        // 查看任务是否在正常执行之前结束,正常true
        boolean cancelled = scheduledFuture.isCancelled();
        while(!cancelled){
            scheduledFuture.cancel(true);
        }
    }
}
