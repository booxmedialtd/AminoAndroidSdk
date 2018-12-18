package com.aminocom.sdk.model.network.steam;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AttributesItem {

    @SerializedName("protection_types")
    public List<String> protectionTypes;

    @SerializedName("streaming_protocols")
    public List<String> streamingProtocols;

    public int bitrate;

    @SerializedName("transport_protocol")
    public String transportProtocol;

    @SerializedName("stream_url")
    public String streamUrl;
}