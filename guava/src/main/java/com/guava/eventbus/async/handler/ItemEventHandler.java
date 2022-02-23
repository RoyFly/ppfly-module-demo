package com.guava.eventbus.async.handler;

import com.guava.eventbus.async.domain.EventMsg;
import com.guava.eventbus.async.domain.EventTypeEnum;
import com.guava.eventbus.async.listener.EventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 产品事件处理器
 */
@Slf4j
@Component
public class ItemEventHandler implements EventHandler {

    @Autowired
    private EventListener eventListener;

    @PostConstruct
    public void register() {
        eventListener.register(EventTypeEnum.ITEM.name(), this);
    }

    @Override
    public boolean  execute(EventMsg event) {
        try {
            //测试请求是异步的~~~~时间大家可以设置小一点~~~
            Thread.sleep(5000l);
            log.info("我是产品处理器！");
            return false;
        } catch (Exception e) {
            log.error("产品处理失败",e);
        }
        return true;
    }
}