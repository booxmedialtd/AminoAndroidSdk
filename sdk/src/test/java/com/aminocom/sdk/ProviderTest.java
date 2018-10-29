package com.aminocom.sdk;

import com.aminocom.sdk.model.client.channel.Channel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import io.reactivex.observers.TestObserver;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

public class ProviderTest {
    private Provider provider;
    private MockWebServer mockServer;

    @Before
    public void setUp() throws Exception {
        mockServer = new MockWebServer();

        mockServer.start();

        provider = new Provider();
    }

    @Test
    public void getChannels() throws Exception {
        TestObserver testObserver = new TestObserver<List<Channel>>();

        //String path = "json";

        MockResponse mockResponse = new MockResponse()
                .setResponseCode(200)
                .setBody(getJson("json/channel_response_4_items.json"));

        mockServer.enqueue(mockResponse);

        provider.getChannels().subscribe(testObserver);
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS);

        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);

        RecordedRequest request = mockServer.takeRequest();

        //assertEquals(path, request.getPath());
    }

    @After
    public void tearDown() throws Exception {
        mockServer.shutdown();
    }

    private String getJson(String fileName) {

        StringBuilder result = new StringBuilder();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}