package com.aminocom.sdk.mapper;

import com.aminocom.sdk.model.client.ServiceState;
import com.aminocom.sdk.model.network.ServiceStateElement;
import com.aminocom.sdk.model.network.channel.LiveElement;

class ServiceStateMapper {
    private ServiceStateMapper() {
    }

    static ServiceState from(ServiceStateElement element) {
        ServiceState result = new ServiceState();

        result.setEnabled(element.enabled);
        result.setAuthorized(element.commercial);
        result.setBlackListed(element.blacklisted);

        return result;
    }

    static ServiceState from(LiveElement element) {
        ServiceState result = new ServiceState();

        result.setEnabled(element.enabled);
        result.setAuthorized(element.commercial);
        result.setBlackListed(false);

        return result;
    }
}