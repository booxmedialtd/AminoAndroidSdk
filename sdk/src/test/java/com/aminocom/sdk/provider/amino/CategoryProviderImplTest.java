package com.aminocom.sdk.provider.amino;

import com.aminocom.sdk.JsonReader;
import com.aminocom.sdk.Sdk;
import com.aminocom.sdk.TestCookieManager;
import com.aminocom.sdk.TestLocalRepository;
import com.aminocom.sdk.model.client.Category;
import com.aminocom.sdk.model.network.UserResponse;
import com.aminocom.sdk.provider.ProviderType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;
import okhttp3.mockwebserver.MockWebServer;

// TODO: check correctness of TestCookieManager
public class CategoryProviderImplTest {
    private MockWebServer mockServer;
    private JsonReader jsonReader;
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
    public void getCategories_GuestUser() throws Exception {

        TestSubscriber<List<Category>> testObserver = new TestSubscriber<>();

        sdk.categories().getCategories().subscribe(testObserver);
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS);

        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
    }

    // FIXME: Fix mocking of the server.
    @Test
    public void getCategories_AuthorizedUser() throws Exception {

        TestObserver<UserResponse> loginObserver = new TestObserver<>();

        String user = "aleksei@test.com";
        String password = "1234";

        sdk.user().login(user, password).subscribe(loginObserver);
        loginObserver.awaitTerminalEvent(1, TimeUnit.SECONDS);

        loginObserver.assertNoErrors();
        loginObserver.assertValueCount(1);

        TestSubscriber<List<Category>> categoryObserver = new TestSubscriber<>();

        sdk.categories().getCategories().subscribe(categoryObserver);
        categoryObserver.awaitTerminalEvent(3, TimeUnit.SECONDS);

        categoryObserver.assertNoErrors();
        categoryObserver.assertValueCount(1);
    }

    @After
    public void tearDown() throws Exception {
        mockServer.shutdown();
    }
}