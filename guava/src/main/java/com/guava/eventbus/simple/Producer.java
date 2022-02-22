package com.guava.eventbus.simple;

import com.google.common.eventbus.EventBus;

public class Producer {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        //注册Listener
        eventBus.register(new SimpleListener());
        System.out.println("生产者开始生产数据...");
        //向订阅者发送消息
        eventBus.post("Simple Event");
    }
}
