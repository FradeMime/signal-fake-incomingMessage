package com.company;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import javax.validation.Valid;
import java.util.List;

public class IncomingMessageList {

    @JsonProperty
    @NotNull
    @Valid
    private List<com.company.IncomingMessage> messages;

    @JsonProperty
    private long timestamp;

    @JsonProperty
    private boolean online;

    public IncomingMessageList() {
    }

    public IncomingMessageList(List<com.company.IncomingMessage> messages, boolean online) {
        this.messages = messages;
        this.online = online;
    }

    public void setMessages(List<com.company.IncomingMessage> messages) {
        this.messages = messages;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public List<com.company.IncomingMessage> getMessages() {
        return messages;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public boolean isOnline() {
        return online;
    }
}