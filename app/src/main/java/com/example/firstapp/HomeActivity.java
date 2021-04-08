package com.example.firstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navigation = findViewById(R.id.bottom_navigation);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent int1;
                switch (item.getItemId()){
                    case R.id.home:
                        //startActivity(new Intent(HomeActivity.this,HomeActivity.class));
                        Toast.makeText(HomeActivity.this, "HOME", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.search:
                        loadFragment(new SearchFragment());
                        Toast.makeText(HomeActivity.this, "SEARCH", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.cart:
                        loadFragment(new CartFragment());
                        Toast.makeText(HomeActivity.this, "CART", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.profile:
                        Toast.makeText(HomeActivity.this, "PROFILE", Toast.LENGTH_SHORT).show();
                        int1 = new Intent(HomeActivity.this,ProfileActivity.class);
                        startActivity(int1);
                        break;
                }

                return true;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fm_trans = fm.beginTransaction();
        fm_trans.replace(R.id.container,fragment);
        fm_trans.commit();
    }
}