package com.guava.eventbus.exception;

import com.google.common.eventbus.EventBus;

public class Producer {
    /**
     * 在默认情况下，EventBus不会对异常信息进行处理，异常信息也不会终止EventBus的运行，只会简单的打印出异常堆栈信息。
     * 可以在EventBus构造函数中传入一个SubscriberExceptionHandler对象来对异常信息进行处理
     *
     * @param args
     */
    public static void main(String[] args) {
        //在EventBus构造函数中传入SubscriberExceptionHandler来对异常信息进行处理
        //下面是通过lambda表达式来实现SubscriberExceptionHandler接口
        final EventBus eventBus = new EventBus((exception, context) -> {
            System.out.println(exception);//java.lang.RuntimeException
            System.out.println(context.getEvent());//Exception event
            System.out.println(context.getEventBus());//defalut
            System.out.println(context.getSubscriber());//ExceptionListener
            System.out.println(context.getSubscriberMethod());//m3
        });
        eventBus.register(new ExceptionListener());
        eventBus.post("Exception event");
    }
}
