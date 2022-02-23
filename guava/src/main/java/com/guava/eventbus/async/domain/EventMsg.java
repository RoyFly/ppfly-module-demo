package com.guava.eventbus.async.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventMsg {
    private String content;
    private EventTypeEnum eventTypeEnum;
    private boolean async;
}