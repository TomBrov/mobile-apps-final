package com.mindnote.app.activities;

import android.os.Bundle;
import com.mindnote.app.R;

public class CalendarActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_calendar, findViewById(R.id.content_frame));
    }
}
