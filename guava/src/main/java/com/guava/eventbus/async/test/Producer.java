package com.guava.eventbus.async.test;

import com.guava.eventbus.async.config.EventBusConfig;
import com.guava.eventbus.async.domain.EventMsg;
import com.guava.eventbus.async.domain.EventTypeEnum;
import com.guava.eventbus.async.listener.EventListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Producer {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventBusConfig.class);
        //获取EventListener bean
        EventListener eventListener = context.getBean(EventListener.class);
        System.out.println("获取产品列表开始...");
        EventMsg msg = EventMsg.builder()
                .content("111111111")
                .async(true)
                .eventTypeEnum(EventTypeEnum.ITEM)
                .build();
        eventListener.post(msg);
        System.out.println("获取产品列表结束...");
//        context.close();
    }
}
