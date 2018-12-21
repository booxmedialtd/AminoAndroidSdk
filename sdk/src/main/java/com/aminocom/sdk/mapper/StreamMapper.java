package com.aminocom.sdk.mapper;

import com.aminocom.sdk.model.client.Stream;
import com.aminocom.sdk.model.network.stream.StreamItem;

import java.util.ArrayList;
import java.util.List;

public class StreamMapper {
    public static List<Stream> from(String channelId, List<StreamItem> items) {
        List<Stream> result = new ArrayList<>();

        for (StreamItem item : items) {
            result.add(from(channelId, item));
        }

        return result;
    }

    public static Stream from(String channelId, StreamItem item) {
        Stream stream = new Stream();

        stream.setServiceId(item.id);
        stream.setChannelId(channelId);
        stream.setBitrate(item.attributes.bitrate);
        stream.setTransportProtocol(item.attributes.transportProtocol);
        stream.setStreamUrl(item.attributes.streamUrl);

        List<String> protectionTypes = item.attributes.protectionTypes;

        String streamProtection = (protectionTypes != null && protectionTypes.size() > 0) ? protectionTypes.get(0) : "";
        stream.setProtection(streamProtection);

        List<String> streamingProtocols = item.attributes.streamingProtocols;

        String streamProtocol = (streamingProtocols != null && streamingProtocols.size() > 0) ? streamingProtocols.get(0) : "";
        stream.setStreamProtocol(streamProtocol);

        return stream;
    }
}