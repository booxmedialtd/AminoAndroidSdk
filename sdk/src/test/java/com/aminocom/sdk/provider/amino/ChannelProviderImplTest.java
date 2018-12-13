package com.aminocom.sdk.provider.amino;

import com.aminocom.sdk.JsonReader;
import com.aminocom.sdk.Sdk;
import com.aminocom.sdk.TestCookieManager;
import com.aminocom.sdk.TestLocalRepository;
import com.aminocom.sdk.model.client.channel.Channel;
import com.aminocom.sdk.provider.ProviderType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.subscribers.TestSubscriber;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static junit.framework.TestCase.assertEquals;

// TODO: check correctness of TestCookieManager
public class ChannelProviderImplTest {
    private MockWebServer mockServer;
    private JsonReader jsonReader = new JsonReader();
    private Sdk sdk;

    @Before
    public void setUp() throws Exception {
        mockServer = new MockWebServer();

        mockServer.start();

        sdk = new Sdk(
                "https://nebtest1.auto.neb.amo.booxmedia.xyz/",
                "mobileclient",
                "qn05BON1hXGCUsw",
                ProviderType.AMINO,
                new TestCookieManager(),
                new TestLocalRepository());
    }

    // FIXME: Fix mocking of the server
    @Test
    public void getChannels() {
        TestSubscriber<List<Channel>> testObserver = new TestSubscriber<>();

        //String path = "json";

        MockResponse mockResponse = new MockResponse()
                .setResponseCode(400)
                .setBody(jsonReader.getJson("json/channel_response_4_items.json"));

        //mockServer.enqueue(new MockResponse().setBody("Digest request").setResponseCode(200));
        mockServer.enqueue(mockResponse);

        sdk.channels().getChannels().subscribe(testObserver);
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS);

        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        assertEquals(4, testObserver.values().get(0).size());

        //RecordedRequest request = mockServer.takeRequest();

        //assertEquals(path, request.getPath());
    }

    @After
    public void tearDown() throws Exception {
        mockServer.shutdown();
    }
}