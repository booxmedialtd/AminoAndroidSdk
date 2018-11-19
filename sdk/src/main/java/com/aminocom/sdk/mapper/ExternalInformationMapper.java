package com.aminocom.sdk.mapper;

import com.aminocom.sdk.model.client.ExternalInformation;
import com.aminocom.sdk.model.network.epg.ExternalInformationElement;

import java.util.ArrayList;
import java.util.List;

class ExternalInformationMapper {
    private ExternalInformationMapper() {
    }

    public static List<ExternalInformation> from(List<ExternalInformationElement> elements) {
        List<ExternalInformation> result = new ArrayList<>();

        for (ExternalInformationElement element : elements) {
            result.add(from(element));
        }

        return result;
    }

    public static ExternalInformation from(ExternalInformationElement element) {
        ExternalInformation result = new ExternalInformation();

        result.setProvider(element.provider);
        result.setExternalShowingId(element.externalShowingId);
        result.setExternalProgramId(element.externalProgramId);

        return result;
    }
}