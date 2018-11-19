package com.aminocom.aminoandroidsdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aminocom.sdk.AndroidCookieManager;
import com.aminocom.sdk.DbRepository;
import com.aminocom.sdk.Sdk;
import com.aminocom.sdk.model.client.Category;
import com.aminocom.sdk.model.client.channel.Channel;
import com.aminocom.sdk.provider.ProviderType;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView testText = findViewById(R.id.test_text);
        Button loginButton = findViewById(R.id.login_button);
        Button channelsButton = findViewById(R.id.channels_button);
        Button categoriesButton = findViewById(R.id.categories_button);
        Button epgButton = findViewById(R.id.epg_button);

        /*Sdk sdk = new Sdk(
                "https://nebtest1.auto.neb.amo.booxmedia.xyz/",
                "mobileclient",
                "qn05BON1hXGCUsw",
                ProviderType.AMINO,
                new AndroidCookieManager(),
                new DbRepository(getApplicationContext())*/

        Sdk sdk = new Sdk(
                "https://tv.dna.fi/",
                "dnaclient",
                "dn4c1!3nt",
                ProviderType.AMINO,
                new AndroidCookieManager(),
                new DbRepository(getApplicationContext())
        );

        loginButton.setOnClickListener(view -> disposable.add(
                //sdk.user().login("aleksei@test.com", "1234")
                sdk.user().login("bt1@dna.fi", "1234")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                items -> Toast.makeText(this, "Login successful", Toast.LENGTH_LONG).show(),
                                t -> Log.e(TAG, "Failed to login", t)
                        ))
        );

        channelsButton.setOnClickListener(view -> disposable.add(
                sdk.channels().getChannels()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(items -> {
                                    StringBuilder text = new StringBuilder();

                                    for (Channel channel : items) {
                                        text.append(channel.getTitle()).append("\n");
                                    }

                                    testText.setText(text.toString());
                                },
                                t -> Log.e(TAG, "Failed to get channels", t)
                        )
                )
        );

        categoriesButton.setOnClickListener(view -> disposable.add(
                sdk.categories().getCategories()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(items -> {
                                    StringBuilder text = new StringBuilder();

                                    for (Category category : items) {
                                        text
                                                .append("Category name: ")
                                                .append(category.getTitle())
                                                .append(" Program size: ")
                                                .append(category.getPrograms().size())
                                                .append("\n");
                                    }

                                    testText.setText(text.toString());
                                },
                                t -> Log.e(TAG, "Failed to load categories", t))
                )
        );

        epgButton.setOnClickListener(view -> disposable.add(
                sdk.epg().getTodayEpg()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(items -> {

                                },
                                t -> Log.e(TAG, "Failed to get channels", t)
                        )
                )
        );
    }

    @Override
    protected void onDestroy() {

        if (disposable != null) {
            disposable.dispose();
        }

        super.onDestroy();
    }
}