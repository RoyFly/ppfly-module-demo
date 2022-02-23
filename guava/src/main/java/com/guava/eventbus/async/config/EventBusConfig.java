package com.guava.eventbus.async.config;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
@ComponentScan("com.guava.eventbus.async")
public class EventBusConfig {

    /**
     * EventBus异步分发
     *
     * @return
     */
    @Bean
    public AsyncEventBus asyncEventBus() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("asyncEventBus").build();
        ExecutorService executorService = new ThreadPoolExecutor(5, 10,
                10000L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(64), namedThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
        return new AsyncEventBus(executorService);
    }

    @Bean
    public EventBus eventBus() {
        return new EventBus();
    }
}