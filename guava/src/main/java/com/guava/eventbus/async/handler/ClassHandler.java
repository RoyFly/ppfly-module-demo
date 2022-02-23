package com.guava.eventbus.async.handler;

import com.guava.eventbus.async.domain.EventMsg;
import com.guava.eventbus.async.domain.EventTypeEnum;
import com.guava.eventbus.async.listener.EventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 类别事件处理器
 */
@Slf4j
@Component
public class ClassHandler implements EventHandler {
    @Autowired
    private EventListener eventListener;

    @PostConstruct
    public void register() {
        eventListener.register(EventTypeEnum.CLASS.name(), this);
    }

    @Override
    public boolean execute(EventMsg event) {
        log.info("我是分类处理器！");
        return false;
    }
}
