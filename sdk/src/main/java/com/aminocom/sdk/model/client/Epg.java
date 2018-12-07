package com.aminocom.sdk.model.client;

import com.aminocom.sdk.model.client.channel.Channel;

import java.util.List;

public class Epg {
    private Channel channel;
    private List<Program> programs;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }
}