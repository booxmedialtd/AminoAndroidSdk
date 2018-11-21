package com.aminocom.sdk.mapper;

import com.aminocom.sdk.model.client.Program;
import com.aminocom.sdk.model.client.channel.Channel;
import com.aminocom.sdk.model.client.channel.LiveChannel;

import java.util.List;

public class LiveChannelMapper {
    private LiveChannelMapper() {
    }

    public static LiveChannel from(Channel channel, List<Program> pendingPrograms) {
        LiveChannel result = new LiveChannel();

        result.setChannel(channel);
        result.setPendingPrograms(pendingPrograms);

        return result;
    }
}