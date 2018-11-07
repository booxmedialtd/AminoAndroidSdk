package com.aminocom.sdk.mapper;

import com.aminocom.sdk.model.client.channel.Ott;
import com.aminocom.sdk.model.client.channel.OttStream;
import com.aminocom.sdk.model.network.channel.OttElement;
import com.aminocom.sdk.model.network.channel.OttStreamItem;

import java.util.ArrayList;
import java.util.List;

class OttMapper {
    static Ott from(OttElement element) {
        Ott result = new Ott();

        result.setLcn(element.lcn);
        result.setMobileLcn(element.mobileLcn);
        result.setStreams(mapStreams(element.streams));

        return result;
    }

    private static List<OttStream> mapStreams(List<OttStreamItem> items) {
        List<OttStream> result = new ArrayList<>();

        for (OttStreamItem item : items) {
            OttStream stream = new OttStream();

            stream.setId(item.id);
            stream.setCodecInfo(item.codecInfo);
            stream.setProtection(item.protection);
            stream.setStreamingProtocol(item.streamingProtocol);
            stream.setTransportProtocol(item.transportProtocol);
            stream.setUrl(item.url);

            result.add(stream);
        }

        return result;
    }
}