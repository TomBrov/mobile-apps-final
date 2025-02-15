package com.mindnote.app.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mindnote.app.R;
import com.mindnote.app.adapters.EntriesAdapter;
import com.mindnote.app.models.Entry;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {
    private EntriesAdapter entriesAdapter;
    private List<Entry> entryList;
    private EditText editTextEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_home, findViewById(R.id.content_frame)); // Load home content

        editTextEntry = findViewById(R.id.editTextEntry);
        Button btnAddEntry = findViewById(R.id.btnAddEntry);
        RecyclerView recyclerView = findViewById(R.id.recentEntriesRecyclerView);

        entryList = new ArrayList<>();
        entriesAdapter = new EntriesAdapter(entryList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(entriesAdapter);

        btnAddEntry.setOnClickListener(v -> {
            String noteText = editTextEntry.getText().toString().trim();
            if (!noteText.isEmpty()) {
                entryList.add(new Entry(noteText));
                entriesAdapter.notifyItemInserted(entryList.size() - 1);
                editTextEntry.setText("");
            } else {
                Toast.makeText(this, "Please enter a note!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
