package com.aminocom.sdk.mapper;

import com.aminocom.sdk.model.client.ExternalInformation;
import com.aminocom.sdk.model.network.epg.ExternalInformationElement;

class ExternalInformationMapper {
    private ExternalInformationMapper() {
    }

    public static ExternalInformation from(ExternalInformationElement element) {
        ExternalInformation result = new ExternalInformation();

        result.setProvider(element.provider);
        result.setExternalShowingId(element.externalShowingId);
        result.setExternalProgramId(element.externalProgramId);

        return result;
    }
}