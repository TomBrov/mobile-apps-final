package com.mindnote.app.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.List;
import com.mindnote.app.R;

public class InspirationActivity extends BaseActivity {
    private ViewPager2 quoteViewPager;
    private List<String> quotesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspiration);

        quoteViewPager = findViewById(R.id.quoteViewPager);
        loadQuotes();
    }

    private void loadQuotes() {
        quotesList = new ArrayList<>();
        quotesList.add("Gratitude turns what we have into enough.");
        quotesList.add("The present moment is filled with joy.");
        quotesList.add("Peace comes from within.");
        quotesList.add("Every day may not be good, but there is something good in every day.");
    }
}
