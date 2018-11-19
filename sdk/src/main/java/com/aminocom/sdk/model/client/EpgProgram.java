package com.aminocom.sdk.model.client;

import java.util.List;

public class EpgProgram {
    private String title;
    private String description;
    private long startTime;
    private int duration;
    private String showId;
    private String programUId;
    private String dvbInformation;
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

    public String getProgramUId() {
        return programUId;
    }

    public void setProgramUId(String programUId) {
        this.programUId = programUId;
    }

    public String getDvbInformation() {
        return dvbInformation;
    }

    public void setDvbInformation(String dvbInformation) {
        this.dvbInformation = dvbInformation;
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