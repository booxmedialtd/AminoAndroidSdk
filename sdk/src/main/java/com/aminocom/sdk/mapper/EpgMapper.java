package com.aminocom.sdk.mapper;

import com.aminocom.sdk.model.client.Epg;
import com.aminocom.sdk.model.client.Program;
import com.aminocom.sdk.model.client.channel.Channel;

import java.util.List;

public class EpgMapper {
    private EpgMapper() {
    }

    public static Epg from(Channel channel, List<Program> programs) {
        Epg result = new Epg();
        result.setChannel(channel);
        result.setPrograms(programs);

        return result;
    }
}