package com.aminocom.sdk.model.network;

import java.util.List;

/**
 * Channel JSON object for API V3
 */
public class ChannelItem {
    private String id;
    private String title;
    private String description;
    private boolean adult;
    private String mediaType;
    private List<Thumbnails> thumbnails;
    private List<ProductItem> products;
    private ChannelServiceElement services;
}