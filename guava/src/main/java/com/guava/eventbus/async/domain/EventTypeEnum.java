package com.guava.eventbus.async.domain;

public enum EventTypeEnum {
    ITEM("产品"),
    CLASS("类别"),
    SPECIFICATION("规格"),
    SERVICE("服务"),
    ADDRESS("地址");
    private String description;

    EventTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}