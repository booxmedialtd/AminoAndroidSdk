package com.aminocom.sdk.model.client.channel;

import java.util.List;

public class Ott {
    private int lcn;
    private int mobileLcn;
    private List<OttStream> streams;

    public int getLcn() {
        return lcn;
    }

    public void setLcn(int lcn) {
        this.lcn = lcn;
    }

    public int getMobileLcn() {
        return mobileLcn;
    }

    public void setMobileLcn(int mobileLcn) {
        this.mobileLcn = mobileLcn;
    }

    public List<OttStream> getStreams() {
        return streams;
    }

    public void setStreams(List<OttStream> streams) {
        this.streams = streams;
    }
}