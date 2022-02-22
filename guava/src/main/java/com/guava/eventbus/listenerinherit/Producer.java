package com.guava.eventbus.listenerinherit;

import com.google.common.eventbus.EventBus;

public class Producer {
    /**
     * 注册了一个Listener，使用eventBus发送消息它的父类的Subscribe也会对此消息进行处理
     *
     * @param args
     */
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new ConcreteListener());
        System.out.println("post the string event.");
        eventBus.post("I am String event");
        System.out.println("post the int event.");
        eventBus.post(1000);
    }
}
