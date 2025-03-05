package com.example.mindnote;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private List<JournalEntry> entries;
    private OnNoteClickListener listener;

    public interface OnNoteClickListener {
        void onNoteClick(JournalEntry entry);
    }

    public NotesAdapter(List<JournalEntry> entries, OnNoteClickListener listener) {
        this.entries = entries;
        this.listener = listener;
    }

    public void updateEntries(List<JournalEntry> newEntries) {
        this.entries = newEntries;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        JournalEntry entry = entries.get(position);
        holder.bind(entry);
    }

    @Override
    public int getItemCount() {
        return entries == null ? 0 : entries.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView dateText;
        private ImageView moodIcon;
        private TextView noteText;
        private TextView tagsText;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            dateText = itemView.findViewById(R.id.dateText);
            moodIcon = itemView.findViewById(R.id.moodIcon);
            noteText = itemView.findViewById(R.id.noteText);
            tagsText = itemView.findViewById(R.id.tagsText);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onNoteClick(entries.get(position));
                }
            });
        }

        public void bind(JournalEntry entry) {
            dateText.setText(entry.getShortDate());
            moodIcon.setImageResource(entry.getMoodIconResource());
            noteText.setText(entry.getNote());

            String tags = entry.getTagsAsString();
            if (tags.isEmpty()) {
                tagsText.setVisibility(View.GONE);
            } else {
                tagsText.setVisibility(View.VISIBLE);
                tagsText.setText(tags);
            }
        }
    }
}