package com.aminocom.aminoandroidsdk.livetv;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aminocom.aminoandroidsdk.R;
import com.aminocom.aminoandroidsdk.App;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LiveTvFragment extends Fragment {

    private static final String TAG = LiveTvFragment.class.getSimpleName();

    private Disposable disposable = null;

    public static Fragment newInstance() {
        return new LiveTvFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_live_tv, parent, false);

        if (getActivity() != null) {
            ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();

            if (ab != null) {
                ab.setTitle(R.string.navigation_live_tv);
            }
        }

        disposable = ((App) getActivity().getApplication()).sdk.channels().getChannels()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(items -> {
                        },
                        t -> Log.e(TAG, "Failed to get channels", t)
                );

        return view;
    }

    @Override
    public void onDestroy() {
        if(disposable != null) {
            disposable.dispose();
        }

        super.onDestroy();
    }
}