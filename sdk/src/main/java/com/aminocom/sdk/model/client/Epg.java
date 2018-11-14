package com.aminocom.sdk.model.client;

import java.util.List;

public class Epg {
    private String channelId;
    private String channelTitle;
    private List<String> networkType;
    private List<Thumbnail> channelLogos;
    private ServiceState live;
    private ServiceState recording;
    private ServiceState catchUp;
    private List<EpgProgram> programs;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public List<String> getNetworkType() {
        return networkType;
    }

    public void setNetworkType(List<String> networkType) {
        this.networkType = networkType;
    }

    public List<Thumbnail> getChannelLogos() {
        return channelLogos;
    }

    public void setChannelLogos(List<Thumbnail> channelLogos) {
        this.channelLogos = channelLogos;
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

    public List<EpgProgram> getPrograms() {
        return programs;
    }

    public void setPrograms(List<EpgProgram> programs) {
        this.programs = programs;
    }
}
