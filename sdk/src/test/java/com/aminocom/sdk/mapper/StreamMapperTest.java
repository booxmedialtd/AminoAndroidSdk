package com.aminocom.sdk.mapper;

import com.aminocom.sdk.JsonReader;
import com.aminocom.sdk.model.client.Stream;
import com.aminocom.sdk.model.network.stream.AttributesItem;
import com.aminocom.sdk.model.network.stream.StreamItem;
import com.google.gson.Gson;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class StreamMapperTest {
    private JsonReader jsonReader = new JsonReader();

    @Test
    public void from() {
        StreamItem streamItem = new Gson().fromJson(jsonReader.getJson("json/stream_item.json"), StreamItem.class);
        Stream stream = StreamMapper.from("1", streamItem);

        assertEquals("{\"stream_url\":\"http:\\/\\/mts-pro-vdn-sslnpvr.dna.fi\",\"profile_name\":\"hd_5\"}", stream.getServiceId());
        assertEquals("1", stream.getChannelId());

        assertEquals(2404, stream.getBitrate());
        assertEquals("http", stream.getTransportProtocol());
        assertEquals("http://mts-pro-vdn-sslnpvr.dna.fi/rec/r2/2018/11/28/451/8187048/8187048.ism/playlist.m3u8?token=d3658830181715a3d80eadc27bd32d88&expires=1545410897", stream.getStreamUrl());
        assertEquals("none", stream.getProtection());
        assertEquals("hls", stream.getStreamProtocol());
    }

    @Test
    public void from_List() {
        List<StreamItem> list = new ArrayList<>();

        list.add(createStreamItem("1", 300));
        list.add(createStreamItem("2", 600));

        List<Stream> result = StreamMapper.from("1", list);

        assertEquals(2, result.size());

        Stream first = result.get(0);
        Stream second = result.get(1);

        assertEquals("stream_url_1", first.getStreamUrl());
        assertEquals("stream_url_2", second.getStreamUrl());

        assertEquals(300, first.getBitrate());
        assertEquals(600, second.getBitrate());
    }

    private StreamItem createStreamItem(String postfix, int bitrate) {
        StreamItem item = new StreamItem();
        item.id = postfix;
        item.type = "type_" + postfix;

        AttributesItem attr = new AttributesItem();
        attr.bitrate = bitrate;
        attr.streamUrl = "stream_url_" + postfix;

        item.attributes = attr;

        return item;
    }
}
