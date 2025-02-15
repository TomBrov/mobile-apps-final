package com.mindnote.app.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.mindnote.app.R;

public class NewEntryActivity extends BaseActivity {
    private EditText editTextEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_new_entry, findViewById(R.id.content_frame));

        editTextEntry = findViewById(R.id.editTextEntry);
        Button btnSave = findViewById(R.id.btnSaveEntry); // Fixed the ID

        btnSave.setOnClickListener(v -> {
            String noteText = editTextEntry.getText().toString().trim();
            if (!noteText.isEmpty()) {
                Toast.makeText(this, "Entry Saved: " + noteText, Toast.LENGTH_SHORT).show();
                finish(); // Close this activity after saving
            } else {
                Toast.makeText(this, "Please enter a note!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
