package com.aminocom.sdk.model.client.channel;

import com.aminocom.sdk.model.client.Program;

public class LiveChannel {
    private Channel channel;
    private Program current;
    private Program next;
    private Program afterNext;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Program getCurrent() {
        return current;
    }

    public void setCurrent(Program current) {
        this.current = current;
    }

    public Program getNext() {
        return next;
    }

    public void setNext(Program next) {
        this.next = next;
    }

    public Program getAfterNext() {
        return afterNext;
    }

    public void setAfterNext(Program afterNext) {
        this.afterNext = afterNext;
    }
}