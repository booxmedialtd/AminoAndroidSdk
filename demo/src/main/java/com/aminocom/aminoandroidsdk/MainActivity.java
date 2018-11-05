package com.aminocom.aminoandroidsdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aminocom.sdk.Provider;
import com.aminocom.sdk.model.client.channel.Channel;

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

        Provider provider = new Provider();

        loginButton.setOnClickListener(view -> disposable.add(
                provider.login("aleksei@test.com", "1234")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                items -> Toast.makeText(this, "Login successful", Toast.LENGTH_LONG).show(),
                                t -> Log.e(TAG, "Failed to login", t)
                        ))
        );

        channelsButton.setOnClickListener(view -> disposable.add(
                provider.getChannels()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(items -> {
                                    StringBuilder text = new StringBuilder();

                                    for (Channel channel : items) {
                                        text.append(channel.getTitle()).append("\n");
                                    }

                                    testText.setText(text.toString());
                                },
                                t -> Log.e(TAG, "Failed to get channels data", t)
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