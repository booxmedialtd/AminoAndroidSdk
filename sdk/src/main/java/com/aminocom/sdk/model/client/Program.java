package com.aminocom.sdk.model.client;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.List;

@Entity(tableName = "programs")
public class Program {
    @PrimaryKey
    @NonNull
    private String programUId = "";
    private String title;
    private String description;
    private long startTime;
    private int duration;
    private String showId;
    private String dvbInformation;
    private String channelId;
    private List<String> categories;
    private List<String> categoryIds;
    private ServiceState recording;
    private ServiceState catchUp;
    private List<ExternalInformation> externalInformation;

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

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    @NonNull
    public String getProgramUId() {
        return programUId;
    }

    public void setProgramUId(@NonNull String programUId) {
        this.programUId = programUId;
    }

    public String getDvbInformation() {
        return dvbInformation;
    }

    public void setDvbInformation(String dvbInformation) {
        this.dvbInformation = dvbInformation;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<String> categoryIds) {
        this.categoryIds = categoryIds;
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

    public List<ExternalInformation> getExternalInformation() {
        return externalInformation;
    }

    public void setExternalInformation(List<ExternalInformation> externalInformation) {
        this.externalInformation = externalInformation;
    }
}