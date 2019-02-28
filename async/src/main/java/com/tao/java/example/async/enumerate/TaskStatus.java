package com.tao.java.example.async.enumerate;


import lombok.Getter;

/**
 * @ClassName TaskInfo
 * @Descriiption 异步任务状态枚举类
 * @Author yanjiantao
 * @Date 2019/2/28 10:02
 **/
@Getter
public enum TaskStatus {
    //任务已经启动
    STARTED(1, "任务已经启动"),
    //任务正在运行
    RUNNING(0, "任务正在运行"),
    //任务执行成功
    SUCCESS(2, "任务执行成功"),
    //任务执行失败
    FAILED(-2, "任务执行失败");
    private int state;
    private String stateInfo;

    TaskStatus(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }
}
