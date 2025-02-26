package com.example.mindnote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private MaterialButton addEntryButton;
    private TextView viewAllButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        addEntryButton = findViewById(R.id.addEntryButton);
        viewAllButton = findViewById(R.id.viewAllButton);

        // Set Home as selected item
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        // Set up bottom navigation listener
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int itemId = item.getItemId();
                        if (itemId == R.id.navigation_journal) {
                            // Handle journal click
                            return true;
                        } else if (itemId == R.id.navigation_notes) {
                            // Handle notes click
                            return true;
                        } else if (itemId == R.id.navigation_home) {
                            // Already on home
                            return true;
                        } else if (itemId == R.id.navigation_calendar) {
                            // Handle calendar click
                            return true;
                        } else if (itemId == R.id.navigation_profile) {
                            // Handle profile click
                            return true;
                        }
                        return false;
                    }
                });

        // Set up add entry button click listener
        addEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start add entry activity
                // Intent intent = new Intent(MainActivity.this, AddEntryActivity.class);
                // startActivity(intent);
                // For now, just show a temporary message
                Toast.makeText(MainActivity.this, "Add new entry clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Set up view all button click listener
        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start all entries activity
                // Intent intent = new Intent(MainActivity.this, AllEntriesActivity.class);
                // startActivity(intent);
                Toast.makeText(MainActivity.this, "View all entries clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Model class for entry
    public static class Entry {
        private String date;
        private String content;
        private int imageResourceId;

        public Entry(String date, String content, int imageResourceId) {
            this.date = date;
            this.content = content;
            this.imageResourceId = imageResourceId;
        }

        public String getDate() {
            return date;
        }

        public String getContent() {
            return content;
        }

        public int getImageResourceId() {
            return imageResourceId;
        }
    }
}