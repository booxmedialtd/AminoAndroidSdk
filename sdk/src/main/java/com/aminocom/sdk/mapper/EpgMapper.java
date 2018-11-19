package com.aminocom.sdk.mapper;

import com.aminocom.sdk.model.client.Program;
import com.aminocom.sdk.model.network.epg.EpgChannelItem;

import java.util.List;

public class EpgMapper {
    private EpgMapper() {
    }

    public static List<Program> from(EpgChannelItem response) {
        return EpgProgramMapper.from(response.id, response.programs);
    }
}