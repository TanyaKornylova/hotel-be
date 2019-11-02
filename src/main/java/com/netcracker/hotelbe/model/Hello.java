package com.netcracker.hotelbe.model;

public class Hello {
    private long id;
    private String content;

    public Hello(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
