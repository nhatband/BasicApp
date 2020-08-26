package com.example.basicapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.basicapp.Fragment.ChartFragment;
import com.example.basicapp.Fragment.HomeFragment;
import com.example.basicapp.Fragment.InfoFragment;
import com.example.basicapp.Fragment.SettingFragment;
import com.example.basicapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SecondScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectfragment = null;
            switch (menuItem.getItemId()) {
                case R.id.nav_Home:
                    selectfragment = new HomeFragment();
                    break;
                case R.id.nav_Info:
                    selectfragment = new InfoFragment();
                    break;
                case R.id.nav_Setting:
                    selectfragment = new SettingFragment();
                    break;
                case R.id.nav_Chart:
                    selectfragment = new ChartFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, selectfragment).commit();
            return true;
        }
    };
}
