package com.aminocom.sdk.model.client.channel;

import java.util.Objects;

public class Dvb {
    private int lcn;
    private String type;
    private String operatorName;
    private String onid;
    private String tsid;
    private String sid;
    private String quality;

    public int getLcn() {
        return lcn;
    }

    public void setLcn(int lcn) {
        this.lcn = lcn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOnid() {
        return onid;
    }

    public void setOnid(String onid) {
        this.onid = onid;
    }

    public String getTsid() {
        return tsid;
    }

    public void setTsid(String tsid) {
        this.tsid = tsid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    @Override
    public String toString() {
        return String.valueOf(lcn);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dvb dvb = (Dvb) o;
        return lcn == dvb.lcn;
    }

    @Override
    public int hashCode() {

        return Objects.hash(lcn);
    }
}
