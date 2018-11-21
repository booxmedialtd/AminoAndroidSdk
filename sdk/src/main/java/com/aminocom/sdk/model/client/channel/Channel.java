package com.aminocom.sdk.model.client.channel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.aminocom.sdk.model.client.Product;
import com.aminocom.sdk.model.client.ServiceState;
import com.aminocom.sdk.model.client.Thumbnail;

import java.util.List;
import java.util.Objects;

/**
 *
 */
@Entity(tableName = "channels")
public class Channel {
    public Channel() {
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "channel_id")
    private String id = "";

    @ColumnInfo(name = "channel_title")
    private String title;

    @ColumnInfo(name = "channel_description")
    private String description;

    private boolean adult;
    private String mediaType;

    @ColumnInfo(name = "channel_thumbnails")
    private List<Thumbnail> thumbnails;
    private List<Product> products;
    private Ott ott;
    private List<Dvb> dvbs;
    @ColumnInfo(name = "channel_live")
    private ServiceState live;
    @ColumnInfo(name = "channel_recording")
    private ServiceState recording;
    @ColumnInfo(name = "channel_catchup")
    private ServiceState catchUp;
    @ColumnInfo(name = "channel_fast_forward")
    private FastForwardState fastForward;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
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

    public Ott getOtt() {
        return ott;
    }

    public void setOtt(Ott ott) {
        this.ott = ott;
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
    @NonNull
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return Objects.equals(id, channel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}