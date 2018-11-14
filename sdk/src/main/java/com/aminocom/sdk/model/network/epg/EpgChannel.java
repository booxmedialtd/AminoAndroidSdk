package com.aminocom.sdk.model.network.epg;

import com.aminocom.sdk.model.network.ThumbnailElement;

import java.util.List;

public class EpgChannel {
    public String id;

    public String title;

    public List<String> networkType;

    public List<EpgServiceElement> services;

    public List<ThumbnailElement> logos;

    public List<EpgProgram> programs;
}