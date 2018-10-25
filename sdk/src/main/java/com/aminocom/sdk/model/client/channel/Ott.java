package com.aminocom.sdk.model.client.channel;

import java.util.Objects;

public class Ott {
    private int id;
    private String codecInfo;
    private String protection;
    private String streamingProtocol;
    private String transportProtocol;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodecInfo() {
        return codecInfo;
    }

    public void setCodecInfo(String codecInfo) {
        this.codecInfo = codecInfo;
    }

    public String getProtection() {
        return protection;
    }

    public void setProtection(String protection) {
        this.protection = protection;
    }

    public String getStreamingProtocol() {
        return streamingProtocol;
    }

    public void setStreamingProtocol(String streamingProtocol) {
        this.streamingProtocol = streamingProtocol;
    }

    public String getTransportProtocol() {
        return transportProtocol;
    }

    public void setTransportProtocol(String transportProtocol) {
        this.transportProtocol = transportProtocol;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ott ott = (Ott) o;
        return id == ott.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
