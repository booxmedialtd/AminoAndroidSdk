package com.aminocom.sdk.model.network.recording.group;

import com.aminocom.sdk.model.network.recording.ProgramItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecordingGroupItem {

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

    @SerializedName("programs")
    public List<ProgramItem> programs;
}