package com.aminocom.aminoandroidsdk;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.aminocom.aminoandroidsdk.category.CategoryFragment;
import com.aminocom.aminoandroidsdk.epg.EpgFragment;
import com.aminocom.aminoandroidsdk.livetv.LiveTvFragment;
import com.aminocom.sdk.AndroidCookieManager;
import com.aminocom.sdk.DbRepository;
import com.aminocom.sdk.Sdk;
import com.aminocom.sdk.provider.ProviderType;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private CompositeDisposable disposable = new CompositeDisposable();
    private Sdk sdk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        sdk = new Sdk(
                "https://tv.dna.fi/",
                "dnaclient",
                "dn4c1!3nt",
                ProviderType.AMINO,
                new AndroidCookieManager(),
                new DbRepository(getApplicationContext())
        );

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        int id = item.getItemId();

        if (id == R.id.nav_live_tv) {
            fragment = LiveTvFragment.newInstance(sdk);
        } else if (id == R.id.nav_categories) {
            fragment = CategoryFragment.newInstance(sdk);
        } else if (id == R.id.nav_epg) {
            fragment = EpgFragment.newInstance(sdk);
        } else if (id == R.id.nav_login) {
            disposable.add(
                    //sdk.user().login("aleksei@test.com", "1234")
                    sdk.user().login("bt1@dna.fi", "1234")
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    items -> Toast.makeText(this, "Login successful", Toast.LENGTH_LONG).show(),
                                    t -> Log.e(TAG, "Failed to login", t)
                            ));
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment, fragment.getClass().getSimpleName())
                    .commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    protected void onDestroy() {

        if (disposable != null) {
            disposable.dispose();
        }

        super.onDestroy();
    }
}