package com.mohancm.newsapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    final Fragment topNewsFragment = new TopNewsFragment();
    final Fragment headlineFragment = new CategoryFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = null;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_topnews:
                    fm.beginTransaction().hide(fragment).show(topNewsFragment).commit();
                    fragment = topNewsFragment;
                    return true;
                case R.id.navigation_category:
                    fm.beginTransaction().hide(fragment).show(headlineFragment).commit();
                    fragment = headlineFragment;
                    return true;
                case R.id.navigation_settings:
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm.beginTransaction().add(R.id.frameLayout, headlineFragment, getString(R.string.two)).hide(headlineFragment).commit();
        fm.beginTransaction().add(R.id.frameLayout, topNewsFragment, getString(R.string.one)).commit();

        fragment = topNewsFragment;
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        BottomNavigationView navigation = findViewById(R.id.bottom_navigationBar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
