package com.guava.eventbus.exception;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionListener.class);

    @Subscribe
    public void m1(final String event){
        if (LOGGER.isInfoEnabled()){
            LOGGER.info("Received event [{}] and will take m1", event);
        }
    }
//    @Subscribe
//    public void m2(final String event){
//        if (LOGGER.isInfoEnabled()){
//            LOGGER.info("Received event [{}] and will take m2", event);
//        }
//    }
    @Subscribe
    public void m3(final String event){
        throw new RuntimeException();
    }
}
