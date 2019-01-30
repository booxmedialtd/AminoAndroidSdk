package com.aminocom.sdk.provider.network;

public interface Providers {
    UserProvider user();
    ChannelProvider channels();
    CategoryProvider categories();
    EpgProvider epg();
    RecordingProvider recording();
    StreamProvider stream();
}