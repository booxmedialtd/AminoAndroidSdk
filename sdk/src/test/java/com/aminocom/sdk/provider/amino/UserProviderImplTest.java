package com.aminocom.sdk.provider.amino;

import com.aminocom.sdk.JsonReader;
import com.aminocom.sdk.Sdk;
import com.aminocom.sdk.TestCookieManager;
import com.aminocom.sdk.TestLocalRepository;
import com.aminocom.sdk.model.network.UserResponse;
import com.aminocom.sdk.provider.ProviderType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.observers.TestObserver;
import okhttp3.mockwebserver.MockWebServer;

import static org.junit.Assert.assertEquals;

// TODO: check correctness of TestCookieManager
public class UserProviderImplTest {
    private MockWebServer mockServer;
    private JsonReader jsonReader;

    @Before
    public void setUp() throws Exception {
        mockServer = new MockWebServer();

        mockServer.start();
    }

    // FIXME: Fix mocking of the server
    @Test
    public void loginCorrect() throws Exception {
        Sdk sdk = new Sdk(
                "https://nebtest1.auto.neb.amo.booxmedia.xyz/",
                "mobileclient",
                "qn05BON1hXGCUsw",
                ProviderType.AMINO,
                new TestCookieManager(),
                new TestLocalRepository());

        TestObserver<UserResponse> testObserver = new TestObserver<>();

        String user = "aleksei@test.com";
        String password = "1234";

        /*MockResponse mockResponse = new MockResponse()
                .setResponseCode(200)
                .setBody(getJson("json/login_response.json"));

        mockServer.enqueue(mockResponse);*/

        sdk.user().login(user, password).subscribe(testObserver);
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS);

        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
    }

    // FIXME: Fix mocking of the server
    @Test
    public void loginWrong() throws Exception {
        Sdk sdk = new Sdk(
                "https://nebtest1.auto.neb.amo.booxmedia.xyz/",
                "mobileclient",
                "qn05BON1hXGCUsw",
                ProviderType.AMINO,
                new TestCookieManager(),
                new TestLocalRepository());

        TestObserver<UserResponse> testObserver = new TestObserver<>();

        String user = "some_user@test.com";
        String password = "0000";

        //MockResponse mockResponse = new MockResponse().setResponseCode(401);

        //mockServer.enqueue(mockResponse);

        sdk.user().login(user, password).subscribe(testObserver);
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS);

        assertEquals(1, testObserver.errorCount());
    }

    @After
    public void tearDown() throws Exception {
        mockServer.shutdown();
    }
}