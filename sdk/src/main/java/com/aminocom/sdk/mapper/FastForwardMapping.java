package com.aminocom.sdk.mapper;

import com.aminocom.sdk.model.client.channel.FastForwardState;
import com.aminocom.sdk.model.network.FastForwardElement;

class FastForwardMapping {
    static FastForwardState from(FastForwardElement element) {
        FastForwardState result = new FastForwardState();

        result.setFastForwardPvr(element.fastForwardPvr);
        result.setFastForwardCatchUp(element.fastForwardCatchUp);
        result.setFastForwardStartOver(element.fastForwardStartOver);

        return result;
    }
}