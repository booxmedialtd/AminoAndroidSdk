package com.aminocom.sdk.model.client;

public class ExternalInformation {
    private String provider;
    private String externalProgramId;
    private String externalShowingId;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getExternalProgramId() {
        return externalProgramId;
    }

    public void setExternalProgramId(String externalProgramId) {
        this.externalProgramId = externalProgramId;
    }

    public String getExternalShowingId() {
        return externalShowingId;
    }

    public void setExternalShowingId(String externalShowingId) {
        this.externalShowingId = externalShowingId;
    }
}