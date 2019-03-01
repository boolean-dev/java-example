package com.tao.java.example.async.entity;

import com.tao.java.example.async.enumerate.TaskStatus;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

/**
 * @ClassName TaskInfo
 * @Descriiption 异步任务信息实体类
 * @Author yanjiantao
 * @Date 2019/2/28 10:02
 **/
@Data
@ToString
public class TaskInfo {

    private String taskId;
    private TaskStatus status;
    private Date startTime;
    private Date endTime;
    private long totalTime;
    private Set<String> errCodes;
}
