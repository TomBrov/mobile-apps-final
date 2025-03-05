package com.example.mindnote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private MaterialButton addEntryButton;
    private TextView viewAllButton;
    private TextView streakCountText;
    private TextView entriesCountText;
    private JournalDataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize data manager
        dataManager = JournalDataManager.getInstance(this);

        // Initialize views
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        addEntryButton = findViewById(R.id.addEntryButton);
        viewAllButton = findViewById(R.id.viewAllButton);

        // Update streak and entries count
        updateStats();

        // Set Home as selected item
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        // Set up bottom navigation listener
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int itemId = item.getItemId();
                        if (itemId == R.id.navigation_journal) {
                            // Navigate to Journal activity
                            Intent intent = new Intent(MainActivity.this, JournalActivity.class);
                            startActivity(intent);
                            return true;
                        } else if (itemId == R.id.navigation_notes) {
                            // Navigate to Notes activity
                            Intent intent = new Intent(MainActivity.this, NotesActivity.class);
                            startActivity(intent);
                            return true;
                        } else if (itemId == R.id.navigation_home) {
                            // Already on home
                            return true;
                        } else if (itemId == R.id.navigation_calendar) {
                            // Handle calendar click
                            Toast.makeText(MainActivity.this, "Calendar tab clicked", Toast.LENGTH_SHORT).show();
                            return true;
                        } else if (itemId == R.id.navigation_profile) {
                            // Handle profile click
                            Toast.makeText(MainActivity.this, "Profile tab clicked", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        return false;
                    }
                });

        // Set up add entry button click listener
        addEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Journal activity for new entry
                Intent intent = new Intent(MainActivity.this, JournalActivity.class);
                startActivity(intent);
            }
        });

        // Set up view all button click listener
        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Notes activity to see all entries
                Intent intent = new Intent(MainActivity.this, NotesActivity.class);
                startActivity(intent);
            }
        });

        // Populate recent entries
        updateRecentEntries();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the data when returning to this activity
        updateStats();
        updateRecentEntries();
    }

    private void updateStats() {
        // For now, we'll just set a static streak count
        // In a real app, you'd calculate this based on consecutive days with entries

        // Update total entries count
        int entryCount = dataManager.getEntryCount();

        try {
            // Find and update the streak count TextView
            if (findViewById(R.id.streakCard) != null) {
                View streakCard = findViewById(R.id.streakCard);
                TextView streakText = streakCard.findViewById(android.R.id.text1);
                if (streakText != null) {
                    streakText.setText("7 day streak");  // Static for now
                }
            }

            // Find and update the entries count TextView
            if (findViewById(R.id.entriesCard) != null) {
                View entriesCard = findViewById(R.id.entriesCard);
                TextView entriesText = entriesCard.findViewById(android.R.id.text1);
                if (entriesText != null) {
                    entriesText.setText(entryCount + " total entries");
                }
            }
        } catch (Exception e) {
            // Fallback in case the layout structure isn't exactly as expected
            e.printStackTrace();
        }
    }

    private void updateRecentEntries() {
        // Get recent entries
        List<JournalEntry> entries = dataManager.getAllEntries();

        // For now, we'll just leave the static entries from the XML layout
        // In a real app, you'd dynamically create or update views for each entry
    }
}