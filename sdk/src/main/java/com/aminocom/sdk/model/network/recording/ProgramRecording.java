package com.aminocom.sdk.model.network.recording;

import com.aminocom.sdk.model.network.Stream;
import com.google.gson.annotations.SerializedName;

public class ProgramRecording {
    public Stream stream;

    @SerializedName("recording_id")
    public String recordingId;

    public String status;
}