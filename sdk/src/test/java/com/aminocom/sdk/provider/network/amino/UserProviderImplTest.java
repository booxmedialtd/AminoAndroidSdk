package com.aminocom.sdk.provider.network.amino;

import com.aminocom.sdk.JsonReader;
import com.aminocom.sdk.Sdk;
import com.aminocom.sdk.TestCookieManager;
import com.aminocom.sdk.TestLocalRepository;
import com.aminocom.sdk.TestSettings;
import com.aminocom.sdk.model.network.UserResponse;
import com.aminocom.sdk.provider.network.ProviderType;
import com.aminocom.sdk.settings.Settings;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.observers.TestObserver;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.junit.Assert.assertEquals;

// TODO: check correctness of TestCookieManager
public class UserProviderImplTest {
    private MockWebServer mockServer;
    private JsonReader jsonReader = new JsonReader();
    private Sdk sdk;
    private Settings settings;

    @Before
    public void setUp() throws Exception {
        mockServer = new MockWebServer();

        mockServer.start();

        settings = new TestSettings();

        sdk = new Sdk(
                mockServer.url("/").toString(),
                "mobileclient",
                "qn05BON1hXGCUsw",
                ProviderType.AMINO,
                new TestCookieManager(),
                new TestLocalRepository(),
                settings
        );
    }

    @Test
    public void login_Successful() {
        MockResponse mockResponse = new MockResponse()
                .setResponseCode(200)
                .setBody(jsonReader.getJson("json/login_successful.json"));

        TestObserver<UserResponse> testObserver = login("test@test.com", "1234", mockResponse);

        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
    }

    @Test
    public void login_Successful_SaveUserName() {
        String user = "test@test.com";

        MockResponse mockResponse = new MockResponse()
                .setResponseCode(200)
                .setBody(jsonReader.getJson("json/login_successful.json"));

        login(user, "1234", mockResponse);

        assertEquals(user, settings.getUserName());
    }

    private TestObserver<UserResponse> login(String username, String password, MockResponse mockResponse) {
        TestObserver<UserResponse> testObserver = new TestObserver<>();

        mockServer.enqueue(mockResponse);

        sdk.user().login(username, password).subscribe(testObserver);
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS);

        return testObserver;
    }

    @Test
    public void login_Failed() {
        MockResponse mockResponse = new MockResponse().setResponseCode(401);

        TestObserver<UserResponse> testObserver = login("some_user@test.com", "0000", mockResponse);

        assertEquals(1, testObserver.errorCount());
    }

    @Test
    public void login_Failed_NoUserName() {
        MockResponse mockResponse = new MockResponse().setResponseCode(401);

        login("some_user@test.com", "0000", mockResponse);

        assertEquals("", settings.getUserName());
    }

    @After
    public void tearDown() throws Exception {
        mockServer.shutdown();
    }
}