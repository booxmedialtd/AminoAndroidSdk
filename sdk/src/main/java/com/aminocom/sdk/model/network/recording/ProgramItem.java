package com.aminocom.sdk.model.network.recording;

import java.util.List;

public class ProgramItem {

    public String description;

    public String title;

    public int playbackDuration;

    public int duration;

    public List<ProgramRecording> recordings;

    public boolean continuous;

    public boolean autoDelete;

    public String startTime;

    public List<String> categories;

    public String category;

    public String programUid;

    public boolean favorite;

    public int channelId;

    public String thumbnailUrl;
}