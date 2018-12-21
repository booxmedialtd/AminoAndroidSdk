package com.aminocom.sdk.provider.amino;

import com.aminocom.sdk.JsonReader;
import com.aminocom.sdk.Sdk;
import com.aminocom.sdk.TestCookieManager;
import com.aminocom.sdk.TestLocalRepository;
import com.aminocom.sdk.model.client.Stream;
import com.aminocom.sdk.provider.ProviderType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.observers.TestObserver;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static junit.framework.TestCase.assertEquals;

public class StreamProviderImplTest {
    private MockWebServer mockServer;
    private JsonReader jsonReader = new JsonReader();
    private Sdk sdk;

    @Before
    public void setUp() throws Exception {
        mockServer = new MockWebServer();

        mockServer.start();

        sdk = new Sdk(
                mockServer.url("/").toString(),
                "mobileclient",
                "qn05BON1hXGCUsw",
                ProviderType.AMINO,
                new TestCookieManager(),
                new TestLocalRepository());
    }

    @Test
    public void getLiveStreams_NoErrors() {
        TestObserver<List<Stream>> testObserver = sendLiveRequest();

        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
    }

    @Test
    public void getLiveStreams_PathCorrect() throws Exception {
        sendLiveRequest();

        String path = "/api/v1/channels/17/relationships/streams?service=mobileclient";

        RecordedRequest request = mockServer.takeRequest();
        assertEquals(path, request.getPath());
    }

    @Test
    public void getLiveStreams_ListSizeCorrect() {
        TestObserver<List<Stream>> testObserver = sendLiveRequest();

        assertEquals(3, testObserver.values().get(0).size());
    }

    @Test
    public void getLiveStreams_SortingCorrect() {
        TestObserver<List<Stream>> testObserver = sendLiveRequest();

        List<Stream> list = testObserver.values().get(0);
        Collections.sort(list);

        assertEquals(1024, list.get(0).getBitrate());
        assertEquals(600, list.get(1).getBitrate());
        assertEquals(300, list.get(2).getBitrate());
    }

    private TestObserver<List<Stream>> sendLiveRequest() {
        TestObserver<List<Stream>> testObserver = new TestObserver<>();

        MockResponse mockResponse = new MockResponse()
                .setResponseCode(200)
                .setBody(jsonReader.getJson("json/live_stream_response.json"));

        mockServer.enqueue(mockResponse);

        sdk.stream().getLiveStreams("17").subscribe(testObserver);
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS);

        return testObserver;
    }

    @Test
    public void getRecordingStreams_NoErrors() {
        TestObserver<List<Stream>> testObserver = sendRecordingRequest();

        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
    }

    @Test
    public void getRecordingStreams_PathCorrect() throws Exception {
        sendRecordingRequest();

        String path = "/api/v1/recordings/2788453/relationships/streams?service=mobileclient";

        RecordedRequest request = mockServer.takeRequest();
        assertEquals(path, request.getPath());
    }

    @Test
    public void getRecordingStreams_ListSizeCorrect() {
        TestObserver<List<Stream>> testObserver = sendRecordingRequest();

        assertEquals(2, testObserver.values().get(0).size());
    }

    private TestObserver<List<Stream>> sendRecordingRequest() {
        TestObserver<List<Stream>> testObserver = new TestObserver<>();

        MockResponse mockResponse = new MockResponse()
                .setResponseCode(200)
                .setBody(jsonReader.getJson("json/recording_stream_response.json"));

        mockServer.enqueue(mockResponse);

        sdk.stream().getRecordingStreams("2788453").subscribe(testObserver);
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS);

        return testObserver;
    }

    @After
    public void tearDown() throws Exception {
        mockServer.shutdown();
    }
}