package com.aminocom.sdk.model.network.recording.group;

import com.aminocom.sdk.model.network.recording.ProgramItem;
import com.google.gson.annotations.SerializedName;

class RecordingGroup {

    public static final String TYPE_GROUP = "Group";
    public static final String TYPE_SINGLE = "Single";

    @SerializedName("id")
    public int id;

    @SerializedName("type")
    public String type;

    @SerializedName("title")
    public String title;

    @SerializedName("programsCount")
    public int programsCount;

    @SerializedName("program")
    public ProgramItem program;
}