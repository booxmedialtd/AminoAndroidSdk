package com.aminocom.sdk.provider.amino;

import com.aminocom.sdk.JsonReader;
import com.aminocom.sdk.Sdk;
import com.aminocom.sdk.TestCookieManager;
import com.aminocom.sdk.TestLocalRepository;
import com.aminocom.sdk.TestSettings;
import com.aminocom.sdk.model.client.Category;
import com.aminocom.sdk.provider.ProviderType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.subscribers.TestSubscriber;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static junit.framework.TestCase.assertEquals;

public class CategoryProviderImplTest {
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
                new TestLocalRepository(),
                new TestSettings()
        );
    }

    @Test
    public void getCategories_Successful() throws Exception {
        String path = "/api/category?service=mobileclient";

        MockResponse mockResponse = new MockResponse()
                .setResponseCode(200)
                .setBody(jsonReader.getJson("json/category_response_3_items.json"));

        mockServer.enqueue(mockResponse);

        TestSubscriber<List<Category>> testObserver = new TestSubscriber<>();

        sdk.categories().getCategories().subscribe(testObserver);
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS);

        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        assertEquals(3, testObserver.values().get(0).size());

        RecordedRequest request = mockServer.takeRequest();
        assertEquals(path, request.getPath());
    }

    @Test
    public void getCategories_Failed() {
        MockResponse mockResponse = new MockResponse()
                .setResponseCode(500);

        mockServer.enqueue(mockResponse);

        TestSubscriber<List<Category>> testObserver = new TestSubscriber<>();

        sdk.categories().getCategories().subscribe(testObserver);
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS);

        testObserver.assertNoValues();
        assertEquals(1, testObserver.errorCount());
    }

    @After
    public void tearDown() throws Exception {
        mockServer.shutdown();
    }
}