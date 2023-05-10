package com.example.makemyeventfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import com.example.makemyeventfinal.Test.HomeFragment;
import com.example.makemyeventfinal.Test.UploadEventFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class BaseActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        getSupportActionBar().hide();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ImagesActivity()).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ImagesActivity()).commit();
                        return true;
                    case R.id.upload:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new UploadeventActivity1()).commit();
                        return true;
                    case R.id.love:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new FavActivity()).commit();
                        return true;

                    case R.id.Account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ProfilePage()).commit();
                        return true;
                }
                return false;
            }
        });
    }
}