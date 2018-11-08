package com.aminocom.sdk;

import com.aminocom.sdk.model.client.Category;
import com.aminocom.sdk.model.client.channel.Channel;
import com.aminocom.sdk.model.network.UserResponse;

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
public class ProviderTest {
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
        Provider provider = new Provider(new TestCookieManager());

        TestObserver<List<Channel>> testObserver = new TestObserver<>();

        String path = "json";

        MockResponse mockResponse = new MockResponse()
                .setResponseCode(200)
                .setBody(jsonReader.getJson("json/channel_response_4_items.json"));

        mockServer.enqueue(mockResponse);

        provider.getChannels().subscribe(testObserver);
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS);

        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);

        RecordedRequest request = mockServer.takeRequest();

        assertEquals(path, request.getPath());
    }

    // FIXME: Fix mocking of the server
    @Test
    public void loginCorrect() throws Exception {
        Provider provider = new Provider(new TestCookieManager());

        TestObserver<UserResponse> testObserver = new TestObserver<>();

        String user = "aleksei@test.com";
        String password = "1234";

        /*MockResponse mockResponse = new MockResponse()
                .setResponseCode(200)
                .setBody(getJson("json/login_response.json"));

        mockServer.enqueue(mockResponse);*/

        provider.login(user, password).subscribe(testObserver);
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS);

        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
    }

    // FIXME: Fix mocking of the server
    @Test
    public void loginWrong() throws Exception {
        Provider provider = new Provider(new TestCookieManager());

        TestObserver<UserResponse> testObserver = new TestObserver<>();

        String user = "some_user@test.com";
        String password = "0000";

        //MockResponse mockResponse = new MockResponse().setResponseCode(401);

        //mockServer.enqueue(mockResponse);

        provider.login(user, password).subscribe(testObserver);
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS);

        assertEquals(1, testObserver.errorCount());
    }

    // FIXME: Fix mocking of the server
    @Test
    public void getCategories_NoLogin() throws Exception {
        Provider provider = new Provider(new TestCookieManager());

        TestObserver<List<Category>> testObserver = new TestObserver<>();

        provider.getCategories().subscribe(testObserver);
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS);

        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
    }

    // FIXME: Fix mocking of the server.
    @Test
    public void getCategories_Login() throws Exception {
        Provider provider = new Provider(new TestCookieManager());

        TestObserver<UserResponse> loginObserver = new TestObserver<>();

        String user = "aleksei@test.com";
        String password = "1234";

        provider.login(user, password).subscribe(loginObserver);
        loginObserver.awaitTerminalEvent(1, TimeUnit.SECONDS);

        loginObserver.assertNoErrors();
        loginObserver.assertValueCount(1);

        TestObserver<List<Category>> categoryObserver = new TestObserver<>();

        provider.getCategories().subscribe(categoryObserver);
        categoryObserver.awaitTerminalEvent(3, TimeUnit.SECONDS);

        categoryObserver.assertNoErrors();
        categoryObserver.assertValueCount(1);
    }

    @After
    public void tearDown() throws Exception {
        mockServer.shutdown();
    }
}