package com.aminocom.sdk.model.network.epg;

import com.google.gson.annotations.SerializedName;

public class ExternalInformationElement {
	@SerializedName("provider")
	public String provider;

    @SerializedName("external_program_id")
	public String externalProgramId;

    @SerializedName("provider")
	public String externalShowingId;
}
