package com.cognizant.evaluation.albums.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.cognizant.evaluation.albums.R;
import com.cognizant.evaluation.albums.di.LocalCacheModule;
import com.cognizant.evaluation.albums.di.ApplicationModule;
import com.cognizant.evaluation.albums.di.DaggerAppComponent;
import com.cognizant.evaluation.albums.di.NetworkModule;
import com.cognizant.evaluation.albums.di.MainViewModelFactory;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    MainViewModelFactory mViewModelFactory;
    private ActionBar toolbar;
    private ViewPager pager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new FragmentsPagerAdaptor(getSupportFragmentManager()));
        pager.addOnPageChangeListener(mOnPageChangeListener);
        toolbar = getSupportActionBar();
        toolbar.setTitle(getString(R.string.albums));
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        DaggerAppComponent appComponent = (DaggerAppComponent) DaggerAppComponent.builder().
                networkModule(new NetworkModule())
                .applicationModule(new ApplicationModule(getApplication()))
                .localCacheModule(new LocalCacheModule(getApplication())).build();
        appComponent.generateDependencies(this);
        MainViewModel model = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);
    }


    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (bottomNavigationView != null) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                toolbar.setTitle(bottomNavigationView.getMenu().getItem(position).getTitle());

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_album:
                    pager.setCurrentItem(0);
                    return true;
                case R.id.navigation_photos:
                    pager.setCurrentItem(1);

                    return true;
                case R.id.navigation_users:
                    pager.setCurrentItem(2);

                    return true;
            }
            toolbar.setTitle(item.getTitle());

            return false;
        }
    };

}
