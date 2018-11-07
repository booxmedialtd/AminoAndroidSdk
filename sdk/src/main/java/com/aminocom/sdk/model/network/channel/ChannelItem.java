package com.aminocom.sdk.model.network.channel;

import com.aminocom.sdk.model.network.ProductItem;
import com.aminocom.sdk.model.network.ThumbnailElement;

import java.util.List;

/**
 * Channel JSON object for API V3
 */
public class ChannelItem {
    public String id;
    public String title;
    public String description;
    public boolean adult;
    public String mediaType;
    public List<ThumbnailElement> thumbnails;
    public List<ProductItem> products;
    public ChannelServiceElement services;
}