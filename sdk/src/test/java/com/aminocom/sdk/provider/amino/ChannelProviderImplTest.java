package com.aminocom.sdk.provider.amino;

import com.aminocom.sdk.AndroidCookieManager;
import com.aminocom.sdk.JsonReader;
import com.aminocom.sdk.Sdk;
import com.aminocom.sdk.model.client.channel.Channel;
import com.aminocom.sdk.provider.ProviderType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.observers.TestObserver;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.junit.Assert.assertEquals;

// TODO: check correctness of TestCookieManager
public class ChannelProviderImplTest {
    private MockWebServer mockServer;
    private JsonReader jsonReader;

    @Before
    public void setUp() throws Exception {
        mockServer = new MockWebServer();

        mockServer.start();
    }

    // FIXME: Fix mocking of the server
    @Test
    public void getChannels() throws Exception {
        Sdk sdk = new Sdk(
                "https://nebtest1.auto.neb.amo.booxmedia.xyz/",
                "mobileclient",
                "qn05BON1hXGCUsw",
                ProviderType.AMINO,
                new AndroidCookieManager());

        TestObserver<List<Channel>> testObserver = new TestObserver<>();

        String path = "json";

        MockResponse mockResponse = new MockResponse()
                .setResponseCode(200)
                .setBody(jsonReader.getJson("json/channel_response_4_items.json"));

        mockServer.enqueue(mockResponse);

        sdk.channels().getChannels().subscribe(testObserver);
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS);

        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);

        RecordedRequest request = mockServer.takeRequest();

        assertEquals(path, request.getPath());
    }

    @After
    public void tearDown() throws Exception {
        mockServer.shutdown();
    }
}