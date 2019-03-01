package com.tao.java.example.async.manager;

import com.tao.java.example.async.entity.TaskInfo;
import com.tao.java.example.async.enumerate.TaskStatus;
import com.tao.java.example.async.service.AsyncTaskConstructor;
import com.tao.java.example.async.service.AsyncTaskExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName AsyncTaskManager
 * @Descriiption 异步任务管理类
 * @Author yanjiantao
 * @Date 2019/2/28 10:16
 **/

@Component
public class AsyncTaskManager {

    private Map<String,TaskInfo> taskContainer = new HashMap<>(16);

    @Autowired
    private AsyncTaskExecutor asyncTaskExecutor;

    public TaskInfo initTask() {
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTaskId(getTaskId());
        taskInfo.setStatus(TaskStatus.STARTED);
        taskInfo.setStartTime(new Date());
        setTaskInfo(taskInfo);
        return taskInfo;
    }


    public TaskInfo submit(AsyncTaskConstructor asyncTaskConstructor) {
        TaskInfo info = initTask();
        String taskId = info.getTaskId();
        asyncTaskExecutor.executor(asyncTaskConstructor,taskId);
        return info;
    }


    public void setTaskInfo(TaskInfo taskInfo) {
        taskContainer.put(taskInfo.getTaskId(), taskInfo);
    }

    public TaskInfo getTaskInfo(String taskId) {
        return taskContainer.get(taskId);
    }


    public TaskStatus getTaskStatus(String taskId) {
        return getTaskInfo(taskId).getStatus();
    }

    public String getTaskId() {
        return UUID.randomUUID().toString();
    }
}
