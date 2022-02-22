package com.guava.eventbus.simple;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(SimpleListener.class);

    /**
     * 一个简单的Listener方法
     *
     * @param event Guava规定此处只能有一个参数
     */
    @Subscribe
    public void doAction(final String event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("消费者接受事件 [{}] and will take a action", event);
        }
    }
}
