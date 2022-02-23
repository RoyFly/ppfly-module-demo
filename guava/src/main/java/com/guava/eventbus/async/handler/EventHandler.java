package com.guava.eventbus.async.handler;

import com.guava.eventbus.async.domain.EventMsg;

/**
 * 事件处理器
 */
public interface EventHandler {
    /**
     * 事件执行
     *
     * @param event
     * @return
     */
    boolean  execute(EventMsg event);
}

