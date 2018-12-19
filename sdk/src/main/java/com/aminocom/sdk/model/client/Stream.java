package com.aminocom.sdk.model.client;

public class Stream  implements Comparable<Stream> {

    @SuppressWarnings("unused")
    private static final String LOG_TAG = Stream.class.getSimpleName();

    public static final String HTTP = "http";
    public static final String HTTPS = "https";
    public static final String PROTECTION_NONE = "none";
    public static final String PROTECTION_VERIMATRIX = "VERIMATRIX";

    private String serviceId;
    private int bitrate;
    private String streamType;
    private String protocol;
    private String streamUrl;
    private String protection;
    private String channelId;

    @Override
    public int compareTo(Stream another) {
        return Integer.compare(another.getBitrate(), bitrate);
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getStreamType() {
        return streamType;
    }

    public void setStreamType(String streamType) {
        this.streamType = streamType;
    }

    public String getStreamUrl() {
        return streamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getProtection() {
        return protection;
    }

    public void setProtection(String protection) {
        this.protection = protection;
    }

    public boolean isProtected() {
        return !protection.equalsIgnoreCase(PROTECTION_NONE);
    }
}
