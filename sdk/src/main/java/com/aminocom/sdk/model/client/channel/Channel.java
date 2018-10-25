package com.aminocom.sdk.model.client.channel;

import com.aminocom.sdk.model.client.Product;
import com.aminocom.sdk.model.client.Thumbnail;

import java.util.List;
import java.util.Objects;

/**
 *
 */

public class Channel {
    public Channel() {
    }

    public Channel(String title) {
        this.title = title;
    }

    private long id;
    private String title;
    private String description;
    private boolean adult;
    private String mediaType;
    private List<Thumbnail> thumbnails;
    private List<Product> products;
    private List<Ott> otts;
    private List<Dvb> dvbs;
    private ServiceState live;
    private ServiceState recording;
    private ServiceState catchUp;
    private FastForwardState fastForward;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public List<Thumbnail> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(List<Thumbnail> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Ott> getOtts() {
        return otts;
    }

    public void setOtts(List<Ott> otts) {
        this.otts = otts;
    }

    public List<Dvb> getDvbs() {
        return dvbs;
    }

    public void setDvbs(List<Dvb> dvbs) {
        this.dvbs = dvbs;
    }

    public ServiceState getLive() {
        return live;
    }

    public void setLive(ServiceState live) {
        this.live = live;
    }

    public ServiceState getRecording() {
        return recording;
    }

    public void setRecording(ServiceState recording) {
        this.recording = recording;
    }

    public ServiceState getCatchUp() {
        return catchUp;
    }

    public void setCatchUp(ServiceState catchUp) {
        this.catchUp = catchUp;
    }

    public FastForwardState getFastForward() {
        return fastForward;
    }

    public void setFastForward(FastForwardState fastForward) {
        this.fastForward = fastForward;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Channel channel = (Channel) o;
        return id == channel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}