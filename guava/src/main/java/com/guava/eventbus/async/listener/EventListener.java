package com.guava.eventbus.async.listener;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.guava.eventbus.async.domain.EventMsg;
import com.guava.eventbus.async.handler.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EventListener {
    private static final Logger logger = LoggerFactory.getLogger(EventListener.class);

    private Map<String, List<EventHandler>> enumMap = new ConcurrentHashMap<>();

    @Autowired
    private EventBus eventBus;

    @Autowired
    private AsyncEventBus asyncEventBus;

    /**
     * 注册eventBus
     */
    @PostConstruct
    public void init() {
        eventBus.register(this);
        asyncEventBus.register(this);
    }

    public void register(String eventType, EventHandler eventHandler) {
        logger.info("注册事件处理器,type={}", eventType);
        enumMap.computeIfAbsent(eventType, k -> new ArrayList<>());
        enumMap.get(eventType).add(eventHandler);
    }

    /**
     * 事件处理器
     * 注意：eventBus源码中有四种方法的实现，分别代表不同的功能
     *
     * @param eventMsg
     */
    @Subscribe
    @AllowConcurrentEvents
    public void onEvent(EventMsg eventMsg) {
        execute(eventMsg);
    }

    /**
     * 分发请求
     *
     * @param eventMsg
     */
    public void execute(EventMsg eventMsg) {
        List<EventHandler> lists = enumMap.get(eventMsg.getEventTypeEnum().name());
        if (lists == null) {
            logger.error("事件没有对应的Handler,event:{}", eventMsg.toString());
            return;
        }
        for (EventHandler eventHandler : lists) {
            eventHandler.execute(eventMsg);
        }
    }

    /**
     * 发送请求
     *
     * @param event
     */
    public void post(EventMsg event) {
        try {
            if(event.isAsync()){
                asyncEventBus.post(event);
            }else {
                eventBus.post(event);
            }
        } catch (Exception e) {
            logger.error("asyncEventBus.post()异常,event:{}", event.toString());
        }
    }

}