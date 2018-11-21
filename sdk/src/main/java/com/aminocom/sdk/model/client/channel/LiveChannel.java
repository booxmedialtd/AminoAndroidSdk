package com.aminocom.sdk.model.client.channel;

import com.aminocom.sdk.model.client.Program;

import java.util.List;

public class LiveChannel {
    private Channel channel;
    private List<Program> pendingPrograms;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public List<Program> getPendingPrograms() {
        return pendingPrograms;
    }

    public void setPendingPrograms(List<Program> pendingPrograms) {
        this.pendingPrograms = pendingPrograms;
    }
}