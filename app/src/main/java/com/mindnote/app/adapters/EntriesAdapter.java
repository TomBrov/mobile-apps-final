package com.mindnote.app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mindnote.app.R;
import com.mindnote.app.models.Entry;
import java.util.List;

public class EntriesAdapter extends RecyclerView.Adapter<EntriesAdapter.ViewHolder> {
    private List<Entry> entryList;

    public EntriesAdapter(List<Entry> entryList) {
        this.entryList = entryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_entry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Entry entry = entryList.get(position);
        holder.entryText.setText(entry.getContent());
    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView entryText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            entryText = itemView.findViewById(R.id.entryText);
        }
    }
}
