package com.aminocom.sdk.model.client;

public class RecordingGroup {
    public static final String TYPE_GROUP = "RecordingGroup";
    public static final String TYPE_SINGLE = "Single";

    private int id;

    private String type;

    private String title;

    private int programsCount;

    private String thumbnail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProgramsCount() {
        return programsCount;
    }

    public void setProgramsCount(int programsCount) {
        this.programsCount = programsCount;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
