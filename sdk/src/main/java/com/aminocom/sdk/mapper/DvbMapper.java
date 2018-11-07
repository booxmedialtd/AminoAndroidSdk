package com.aminocom.sdk.mapper;

import com.aminocom.sdk.model.client.channel.Dvb;
import com.aminocom.sdk.model.network.channel.DvbElement;
import com.aminocom.sdk.model.network.channel.TripletItem;

import java.util.ArrayList;
import java.util.List;

class DvbMapper {
    static List<Dvb> from(DvbElement element) {
        List<Dvb> result = new ArrayList<>();

        List<TripletItem> items = element.triplets;

        for (TripletItem item : items) {
            Dvb dvb = new Dvb();

            dvb.setLcn(item.lcn);
            dvb.setType(item.type);
            dvb.setOperatorName(item.operatorName);
            dvb.setOnid(item.onid);
            dvb.setTsid(item.tsid);
            dvb.setSid(item.sid);
            dvb.setQuality(item.quality);

            result.add(dvb);
        }

        return result;
    }
}