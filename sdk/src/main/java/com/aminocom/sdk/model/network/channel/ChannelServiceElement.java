package com.aminocom.sdk.model.network.channel;

import com.aminocom.sdk.model.network.ServiceStateElement;

public class ChannelServiceElement {
	public LiveElement live;
	public ServiceStateElement catchup;
	public ServiceStateElement recording;
	public FastForwardElement fastForward;
}
