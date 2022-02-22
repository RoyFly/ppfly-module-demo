package com.guava.eventbus.multiple;

import com.google.common.eventbus.EventBus;

public class Producer {
    /**
     * eventBus会根据Listener的参数类型的不同，分别向不同的Subscribe发送不同的消息
     *
     * @param args
     */
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new MultipleEventListeners());
        System.out.println("post the string event.");
        eventBus.post("I am String event");
        System.out.println("post the int event.");
        eventBus.post(1000);
    }
}
