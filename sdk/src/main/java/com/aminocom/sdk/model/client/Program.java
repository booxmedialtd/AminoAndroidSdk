package com.aminocom.sdk.model.client;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.aminocom.sdk.model.network.ProgramStatus;

import java.util.List;

@Entity(tableName = "programs")
public class Program {
    @PrimaryKey
    @NonNull
    private String programUId = "";
    private String id;
    private String title;
    private String description;
    private long startTime;
    private long endTime;
    private int duration;
    private String showId;
    private String dvbInformation;
    private String channelId;
    private List<String> categories;
    private List<String> categoryIds;
    private ServiceState recording;
    private ServiceState catchUp;
    private List<ExternalInformation> externalInformation;
    private boolean continuous;
    private boolean autoDelete;
    private boolean favorite;
    private ProgramStatus status;
    private Thumbnail thumbnail;
    private long playbackDuration;
    private String groupId;

    @NonNull
    public String getProgramUId() {
        return programUId;
    }

    public void setProgramUId(@NonNull String programUId) {
        this.programUId = programUId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
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

    public boolean isContinuous() {
        return continuous;
    }

    public void setContinuous(boolean continuous) {
        this.continuous = continuous;
    }

    public boolean isAutoDelete() {
        return autoDelete;
    }

    public void setAutoDelete(boolean autoDelete) {
        this.autoDelete = autoDelete;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public ProgramStatus getStatus() {
        return status;
    }

    public void setStatus(ProgramStatus status) {
        this.status = status;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public long getPlaybackDuration() {
        return playbackDuration;
    }

    public void setPlaybackDuration(long playbackDuration) {
        this.playbackDuration = playbackDuration;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}