package com.aminocom.sdk.mapper;

import com.aminocom.sdk.model.client.channel.ServiceState;
import com.aminocom.sdk.model.network.LiveElement;
import com.aminocom.sdk.model.network.ServiceStateElement;

class ServiceStateMapper {
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