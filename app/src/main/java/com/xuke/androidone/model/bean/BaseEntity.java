package com.xuke.androidone.model.bean;

public class BaseEntity<T> {
    private String command;
    private T content;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
