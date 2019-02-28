package com.tao.java.example.async.manager;

import com.tao.java.example.async.entity.TaskInfo;
import com.tao.java.example.async.enumerate.TaskStatus;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName AsyncTaskMonitor
 * @Descriiption TODO
 * @Author yanjiantao
 * @Date 2019/2/28 15:01
 **/
@Slf4j
@Component
@Aspect
public class AsyncTaskMonitor {

    @Autowired
    private AsyncTaskManager manager;

    @Around("execution(* com.tao.java.example.async.service.AsyncTaskExecutor.*(..))")
    public void taskHandle(ProceedingJoinPoint pjp) {
        //获取taskId
        String taskId = pjp.getArgs()[1].toString();
        //获取任务信息
        TaskInfo taskInfo = manager.getTaskInfo(taskId);
        log.info("AsyncTaskMonitor is monitoring async task:{}", taskId);
        taskInfo.setStatus(TaskStatus.RUNNING);
        manager.setTaskInfo(taskInfo);
        TaskStatus status = null;
        try {
            pjp.proceed();
            status = TaskStatus.SUCCESS;
        } catch (Throwable throwable) {
            status = TaskStatus.FAILED;
            log.error("AsyncTaskMonitor:async task {} is failed.Error info:{}", taskId, throwable.getMessage());
        }
        taskInfo.setEndTime(new Date());
        taskInfo.setStatus(status);
        taskInfo.setTotalTime(0L);
        manager.setTaskInfo(taskInfo);
    }

}
