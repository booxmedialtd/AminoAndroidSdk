package com.aminocom.sdk.mapper;

import com.aminocom.sdk.model.client.channel.Channel;
import com.aminocom.sdk.model.network.ChannelItem;

public class ChannelMapper {
    public static Channel from(ChannelItem response) {
        Channel result = new Channel();

        result.setId(response.id);
        result.setTitle(response.title);
        result.setDescription(response.description);
        result.setAdult(response.adult);
        result.setMediaType(response.mediaType);
        result.setThumbnails(ThumbnailMapper.from(response.thumbnails));

        if (response.products != null) {
            result.setProducts(ProductMapper.from(response.products));
        }

        if (response.services.live.sources.ott != null) {
            result.setOtt(OttMapper.from(response.services.live.sources.ott));
        }

        if (response.services.live.sources.dvb != null) {
            result.setDvbs(DvbMapper.from(response.services.live.sources.dvb));
        }

        if (response.services.live != null) {
            result.setLive(ServiceStateMapper.from(response.services.live));
        }

        if (response.services.recording != null) {
            result.setRecording(ServiceStateMapper.from(response.services.recording));
        }

        if (response.services.catchup != null) {
            result.setCatchUp(ServiceStateMapper.from(response.services.catchup));
        }

        if (response.services.fastForward != null) {
            result.setFastForward(FastForwardMapping.from(response.services.fastForward));
        }


        return result;
    }
}