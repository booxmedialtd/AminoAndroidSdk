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
        result.setProducts(ProductMapper.from(response.products));
        result.setOtt(OttMapper.from(response.services.live.sources.ott));
        result.setDvbs(DvbMapper.from(response.services.live.sources.dvb));
        result.setLive(ServiceStateMapper.from(response.services.live));
        result.setRecording(ServiceStateMapper.from(response.services.recording));
        result.setCatchUp(ServiceStateMapper.from(response.services.catchup));
        result.setFastForward(FastForwardMapping.from(response.services.fastForward));

        return result;
    }
}