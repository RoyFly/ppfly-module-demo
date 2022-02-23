package com.guava.eventbus.eventinherit;

import com.google.common.eventbus.EventBus;

public class Producer {
    /**
     * 当作为参数的event之间有继承关系时，使用eventBus发送消息，event的父类listener也会对此消息进行处理
     *
     * @param args
     */
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new FruitEaterListener());
        eventBus.post(new Apple("apple"));

        System.out.println("---------------------");
        eventBus.post(new Fruit("Fruit"));
    }
}