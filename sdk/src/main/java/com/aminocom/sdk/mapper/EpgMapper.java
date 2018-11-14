package com.aminocom.sdk.mapper;

import com.aminocom.sdk.model.client.Epg;
import com.aminocom.sdk.model.network.epg.EpgChannelItem;

public class EpgMapper {
    private EpgMapper() {
    }

    public static Epg from(EpgChannelItem response) {
        Epg result = new Epg();

        result.setChannelId(response.id);
        result.setChannelTitle(response.title);
        result.setNetworkType(response.networkType);
        result.setChannelLogos(ThumbnailMapper.from(response.logos));
        result.setPrograms(EpgProgramMapper.from(response.programs));

        if (response.services.live != null) {
            result.setLive(ServiceStateMapper.from(response.services.live));
        }

        if (response.services.recording != null) {
            result.setRecording(ServiceStateMapper.from(response.services.recording));
        }

        if (response.services.catchup != null) {
            result.setCatchUp(ServiceStateMapper.from(response.services.catchup));
        }

        return result;
    }
}