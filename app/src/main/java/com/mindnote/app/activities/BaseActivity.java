package com.mindnote.app.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mindnote.app.R;

public class BaseActivity extends AppCompatActivity {
    protected BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    startActivity(new Intent(this, HomeActivity.class));
                    return true;
                case R.id.nav_calendar:
                    startActivity(new Intent(this, CalendarActivity.class));
                    return true;
                case R.id.nav_profile:
                    startActivity(new Intent(this, ProfileActivity.class));
                    return true;
                case R.id.nav_profile:
                    startActivity(new Intent(this, InspirationActivity.class));
                    return true;
                case R.id.nav_profile:
                    startActivity(new Intent(this, NewEntryActivity.class));
                    return true;
            }
            return false;
        });
    }
}
