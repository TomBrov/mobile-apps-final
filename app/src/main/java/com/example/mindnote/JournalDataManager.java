package com.example.mindnote;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JournalDataManager {
    private static final String PREF_NAME = "journal_entries";
    private static final String KEY_ENTRIES = "entries";
    private static JournalDataManager instance;

    private SharedPreferences preferences;
    private List<JournalEntry> entries;
    private Gson gson;

    private JournalDataManager(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
        loadEntries();
    }

    public static synchronized JournalDataManager getInstance(Context context) {
        if (instance == null) {
            instance = new JournalDataManager(context.getApplicationContext());
        }
        return instance;
    }

    private void loadEntries() {
        String entriesJson = preferences.getString(KEY_ENTRIES, null);
        if (entriesJson != null) {
            Type type = new TypeToken<ArrayList<JournalEntry>>() {}.getType();
            entries = gson.fromJson(entriesJson, type);
        } else {
            entries = new ArrayList<>();
        }
    }

    private void saveEntries() {
        String entriesJson = gson.toJson(entries);
        preferences.edit().putString(KEY_ENTRIES, entriesJson).apply();
    }

    public List<JournalEntry> getAllEntries() {
        // Return a sorted copy of the entries (newest first)
        List<JournalEntry> sortedEntries = new ArrayList<>(entries);
        Collections.sort(sortedEntries, new Comparator<JournalEntry>() {
            @Override
            public int compare(JournalEntry e1, JournalEntry e2) {
                return e2.getDate().compareTo(e1.getDate());
            }
        });
        return sortedEntries;
    }

    public void addEntry(JournalEntry entry) {
        entries.add(entry);
        saveEntries();
    }

    public void updateEntry(JournalEntry entry) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getId().equals(entry.getId())) {
                entries.set(i, entry);
                saveEntries();
                return;
            }
        }
    }

    public void deleteEntry(String entryId) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getId().equals(entryId)) {
                entries.remove(i);
                saveEntries();
                return;
            }
        }
    }

    public JournalEntry getEntryById(String entryId) {
        for (JournalEntry entry : entries) {
            if (entry.getId().equals(entryId)) {
                return entry;
            }
        }
        return null;
    }

    public int getEntryCount() {
        return entries.size();
    }

    // For debugging or when user wants to clear all data
    public void clearAllEntries() {
        entries.clear();
        saveEntries();
    }
}