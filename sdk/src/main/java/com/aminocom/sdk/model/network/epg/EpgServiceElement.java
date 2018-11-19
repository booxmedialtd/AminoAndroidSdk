package com.aminocom.sdk.model.network.epg;

import com.aminocom.sdk.model.network.channel.LiveElement;
import com.aminocom.sdk.model.network.ServiceStateElement;
import com.google.gson.annotations.SerializedName;

public class EpgServiceElement {
	@SerializedName("liveTV")
	public LiveElement live;
    @SerializedName("recording")
	public ServiceStateElement recording;
    @SerializedName("catchup")
    public ServiceStateElement catchup;
}