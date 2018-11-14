package com.aminocom.sdk.model.network.epg;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class EpgProgramItem {

    public String title;

    public String description;

    public Date startTime;

    public int duration;

    public String showId;

    public String programUId;

    public String dvbInformation;

    public List<String> categories;

    public List<String> categoryIds;

    public EpgServiceElement services;

    @SerializedName("external_program_information")
    public ExternalInformationElement externalInformation;
}