package com.example.mindnote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.Chip;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class JournalActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ImageButton backButton;
    private ImageButton moreButton;
    private TextView dateText;
    private ImageButton[] moodButtons;
    private EditText gratitudeInput;
    private Chip tagWork, tagFamily, tagHealth, tagPersonal;
    private Button addPhotoButton;
    private Button saveButton;
    private TextView cancelButton;
    private int selectedMoodIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        // Initialize views
        initViews();

        // Set current date
        setCurrentDate();

        // Set up buttons
        setupButtons();

        // Set up bottom navigation
        setupBottomNavigation();
    }

    private void initViews() {
        // Top bar
        backButton = findViewById(R.id.backButton);
        moreButton = findViewById(R.id.moreButton);

        // Date
        dateText = findViewById(R.id.dateText);

        // Mood buttons
        moodButtons = new ImageButton[3];
        moodButtons[0] = findViewById(R.id.moodHappy);
        moodButtons[1] = findViewById(R.id.moodNeutral);
        moodButtons[2] = findViewById(R.id.moodSad);

        // Gratitude input
        gratitudeInput = findViewById(R.id.gratitudeInput);

        // Tags
        tagWork = findViewById(R.id.tagWork);
        tagFamily = findViewById(R.id.tagFamily);
        tagHealth = findViewById(R.id.tagHealth);
        tagPersonal = findViewById(R.id.tagPersonal);

        // Action buttons
        addPhotoButton = findViewById(R.id.addPhotoButton);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);

        // Bottom navigation
        bottomNavigationView = findViewById(R.id.bottomNavigation);
    }

    private void setCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.getDefault());
        String currentDate = dateFormat.format(new Date());
        dateText.setText(currentDate);
    }

    private void setupButtons() {
        // Set up mood buttons
        for (int i = 0; i < moodButtons.length; i++) {
            final int index = i;
            moodButtons[i].setOnClickListener(v -> selectMood(index));
        }

        // Back button returns to MainActivity
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(JournalActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        // More button shows options
        moreButton.setOnClickListener(v -> {
            Toast.makeText(JournalActivity.this, "More options", Toast.LENGTH_SHORT).show();
        });

        // Add photo button
        addPhotoButton.setOnClickListener(v -> {
            Toast.makeText(JournalActivity.this, "Add photo clicked", Toast.LENGTH_SHORT).show();
        });

        // Save button
        saveButton.setOnClickListener(v -> {
            saveEntry();
        });

        // Cancel button
        cancelButton.setOnClickListener(v -> {
            Intent intent = new Intent(JournalActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void selectMood(int index) {
        // Deselect previously selected mood
        if (selectedMoodIndex != -1) {
            moodButtons[selectedMoodIndex].setAlpha(1.0f);
        }

        // Select new mood
        selectedMoodIndex = index;
        moodButtons[selectedMoodIndex].setAlpha(0.7f);

        String[] moodNames = {"Happy", "Neutral", "Sad"};
        Toast.makeText(this, "Selected mood: " + moodNames[index], Toast.LENGTH_SHORT).show();
    }

    private void saveEntry() {
        // Validate input
        if (selectedMoodIndex == -1) {
            Toast.makeText(this, "Please select your mood", Toast.LENGTH_SHORT).show();
            return;
        }

        if (gratitudeInput.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please write a gratitude note", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get selected tags
        StringBuilder tags = new StringBuilder();
        if (tagWork.isChecked()) tags.append("Work, ");
        if (tagFamily.isChecked()) tags.append("Family, ");
        if (tagHealth.isChecked()) tags.append("Health, ");
        if (tagPersonal.isChecked()) tags.append("Personal, ");

        // Remove trailing comma if any
        String tagsString = tags.toString();
        if (tagsString.endsWith(", ")) {
            tagsString = tagsString.substring(0, tagsString.length() - 2);
        }

        // For now, just show a success message
        Toast.makeText(this, "Entry saved successfully!", Toast.LENGTH_SHORT).show();

        // Return to MainActivity
        Intent intent = new Intent(JournalActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void setupBottomNavigation() {
        // Set Journal as selected item
        bottomNavigationView.setSelectedItemId(R.id.navigation_journal);

        // Set up bottom navigation listener
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int itemId = item.getItemId();
                        if (itemId == R.id.navigation_journal) {
                            // Already on journal page
                            return true;
                        } else if (itemId == R.id.navigation_notes) {
                            // Handle notes click
                            return true;
                        } else if (itemId == R.id.navigation_home) {
                            // Return to home
                            Intent intent = new Intent(JournalActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
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
    }
}