package com.tao.java.example.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName AsyncTask
 * @Descriiption 异步任务处理器，继承AsyncTaskConstructor
 * @Author yanjiantao
 * @Date 2019/3/1 14:19
 **/
@Slf4j
@Component
public class AsyncTask implements AsyncTaskConstructor{
    @Override
    public void async() {
        try {
            log.info("执行任务开始.....");
            Thread.sleep(10000L);
            log.info("执行任务结束.....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
