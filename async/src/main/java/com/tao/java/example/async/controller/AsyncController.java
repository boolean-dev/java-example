package com.tao.java.example.async.controller;

import com.tao.java.example.async.entity.TaskInfo;
import com.tao.java.example.async.manager.AsyncTaskManager;
import com.tao.java.example.async.service.AsyncTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AsyncController
 * @Descriiption TODO
 * @Author yanjiantao
 * @Date 2019/2/28 15:11
 **/
@RestController
public class AsyncController {

    @Autowired
    private AsyncTaskManager manager;

    @RequestMapping(value = "/startTask", method = RequestMethod.GET)
    public Object startAsyncTask() {
        //调用任务管理器中的submit去提交一个异步任务
        TaskInfo taskInfo = manager.submit(new AsyncTask());
        return taskInfo;
    }

    @RequestMapping(value = "/getTaskStatus", method = RequestMethod.GET)
    public Object getTaskStatus( @RequestParam("taskId") String taskId) {
        return manager.getTaskInfo(taskId);
    }
}
