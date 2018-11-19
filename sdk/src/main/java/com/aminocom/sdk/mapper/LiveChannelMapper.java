package com.aminocom.sdk.mapper;

import com.aminocom.sdk.model.client.Program;
import com.aminocom.sdk.model.client.channel.Channel;
import com.aminocom.sdk.model.client.channel.LiveChannel;

import java.util.List;

public class LiveChannelMapper {
    private LiveChannelMapper() {
    }

    static LiveChannel from(Channel channel, List<Program> upcomingPrograms) {
        LiveChannel result = new LiveChannel();

        result.setChannel(channel);

        if (upcomingPrograms.size() > 1) {
            result.setCurrent(upcomingPrograms.get(0));

            if (upcomingPrograms.size() > 2) {
                result.setNext(upcomingPrograms.get(1));

                if (upcomingPrograms.size() > 3) {
                    result.setAfterNext(upcomingPrograms.get(2));
                }
            }
        }

        return result;
    }
}