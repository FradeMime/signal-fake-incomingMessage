package com.company;
// simport com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IncomingMessage {

    @JsonProperty
    private int type;

    @JsonProperty
    private String destination;

    @JsonProperty
    private long destinationDeviceId = 1;

    @JsonProperty
    private int destinationRegistrationId;

    @JsonProperty
    private String body;

    @JsonProperty
    private String content;

    @JsonProperty
    private String relay;

    @JsonProperty
    private long timestamp; // deprecated

    public IncomingMessage(int type, String destination, String body, long timestamp) {
        this.type = type;
        this.destination = destination;
        this.body = body;
        this.timestamp = timestamp;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDestination() {
        return destination;
    }

    public String getBody() {
        return body;
    }

    public int getType() {
        return type;
    }

    public String getRelay() {
        return relay;
    }

    public long getDestinationDeviceId() {
        return destinationDeviceId;
    }

    public int getDestinationRegistrationId() {
        return destinationRegistrationId;
    }

    public String getContent() {
        return content;
    }

}