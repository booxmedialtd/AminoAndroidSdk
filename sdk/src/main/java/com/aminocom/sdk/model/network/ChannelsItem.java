package com.aminocom.sdk.model.network;

import java.util.List;

public class ChannelsItem {

    public String id;

    public String title;

    public boolean isUserAuthorized;

    public String mediaType;

    public String thumbnailUrl;

    public String detailUrl;

    public boolean isEvent;

    public String startTime;

    public String endTime;

    public boolean favorite;

    public Object customProperties;

    public int rank;

    public List<String> streamProtection;

    public List<String> labels;

    public List<String> categories;
}