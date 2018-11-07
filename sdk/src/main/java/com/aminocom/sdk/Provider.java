package com.aminocom.sdk;

import android.util.Log;

import com.aminocom.sdk.mapper.CategoryMapper;
import com.aminocom.sdk.mapper.CategoryProgramMapper;
import com.aminocom.sdk.mapper.ChannelMapper;
import com.aminocom.sdk.model.client.Category;
import com.aminocom.sdk.model.client.channel.Channel;
import com.aminocom.sdk.model.network.UserResponse;
import com.aminocom.sdk.model.network.category.CategoryListItem;
import com.aminocom.sdk.util.AccountUtil;
import com.burgstaller.okhttp.CachingAuthenticatorDecorator;
import com.burgstaller.okhttp.digest.CachingAuthenticator;
import com.burgstaller.okhttp.digest.Credentials;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Provider {

    private static final String TAG = Provider.class.getSimpleName();

    private static final String SERVICE = "mobileclient";

    private long channelsCacheTime = 0;
    private long categoriesCacheTime = 0;

    private ServerApi api;
    private LocalRepository localRepository;
    private final CustomDigestAuthenticator authenticator;
    private CookieManager cookieManager;

    public Provider(CookieManager cookieManager) {
        this.cookieManager = cookieManager;

        final HttpLoggingInterceptor.Logger logger = message -> {
            if (!message.isEmpty()) {
                System.out.println("HttpInterceptor: " + message);
            }
        };

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(logger);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Gson gson = new GsonBuilder()
                .setDateFormat(new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH).toPattern())
                .create();

        Credentials serviceCredentials = new Credentials(SERVICE, "qn05BON1hXGCUsw");

        authenticator = new CustomDigestAuthenticator(serviceCredentials, null);

        final Map<String, CachingAuthenticator> authCache = new ConcurrentHashMap<>();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .authenticator(new CachingAuthenticatorDecorator(authenticator, authCache))
                .addInterceptor(new RetrofitInterceptor(authCache, cookieManager))
                .addNetworkInterceptor(interceptor)
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://nebtest1.auto.neb.amo.booxmedia.xyz/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        api = retrofit.create(ServerApi.class);

        localRepository = new CacheRepository();
    }

    // TODO: add correct parameters when SDK Builder will be ready
    public Single<UserResponse> login(String login, String password) {
        authenticator.setUserCredentials(new Credentials(login, password));

        return api.login(
                login,
                "0.0.1",
                AccountUtil.generateUuid(),
                "Android",
                "8.0.1",
                SERVICE
        );
    }

    // TODO: change username when username will be stored in SDK
    public Observable<List<Channel>> getChannels() {
        String userName = cookieManager.isCookieExists() ? "aleksei@test.com" : "guest";

        if (System.currentTimeMillis() - channelsCacheTime > CacheTTLConfig.CHANNEL_TTL) {
            return api.getChannels(userName, SERVICE)
                    .toObservable()
                    .flatMapIterable(response -> response.data.channels)
                    .map(ChannelMapper::from)
                    .toList()
                    .doOnSuccess(channels -> {
                        localRepository.cacheChannels(channels);
                        channelsCacheTime = System.currentTimeMillis();
                    })
                    .flatMapObservable(list -> localRepository.getChannels());
        } else {
            return localRepository.getChannels();
        }
    }

    public Observable<List<Category>> getCategories() {
        if (System.currentTimeMillis() - categoriesCacheTime > CacheTTLConfig.CATEGORY_TTL) {
            return api.getCategoryList(SERVICE)
                    .toObservable()
                    .flatMapIterable(response -> response.categoryList.categories)
                    .flatMapSingle(this::getCategory)
                    .toList()
                    .doOnSuccess(categories -> {
                        localRepository.cacheCategories(categories);
                        categoriesCacheTime = System.currentTimeMillis();
                    })
                    .flatMapObservable(list -> localRepository.getCategories());
        } else {
            return localRepository.getCategories();
        }
    }

    private Single<Category> getCategory(CategoryListItem item) {
        return api.getCategory(item.epgUrl)
                .doOnError(t -> Log.e(TAG, "Failed to load category: " + item.title))
                .toObservable()
                .onExceptionResumeNext(Observable.empty())
                .filter(response -> response.epg.resultSet.totalItems > 0)
                .flatMapIterable(categoryResponse -> categoryResponse.epg.programList.programs)
                .map(CategoryProgramMapper::from)
                .toList()
                .map(programs -> CategoryMapper.from(item, programs));
    }
}