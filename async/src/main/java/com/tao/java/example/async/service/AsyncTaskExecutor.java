package com.tao.java.example.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @ClassName AsyncTaskExecutor
 * @Descriiption TODO
 * @Author yanjiantao
 * @Date 2019/2/28 14:52
 **/
@Slf4j
@Component
public class AsyncTaskExecutor {

    @Async
    public void executor(AsyncTaskConstructor asyncTaskGenerator, String taskInfo) {
        log.info("AsyncTaskExecutor is executing async task:{}", taskInfo);
        asyncTaskGenerator.async();
    }
}
