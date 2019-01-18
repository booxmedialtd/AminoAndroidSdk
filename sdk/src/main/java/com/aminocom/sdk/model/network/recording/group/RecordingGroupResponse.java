package com.aminocom.sdk.model.network.recording.group;

import com.aminocom.sdk.model.network.ResultSet;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecordingGroupResponse{
    @SerializedName("resultSet")
    public ResultSet resultSet;

    @SerializedName("recordingGroups")
    public List<RecordingGroup> recordingGroups;
}