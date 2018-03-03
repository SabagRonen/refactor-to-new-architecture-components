package com.sabag.ronen.refactortonewarchitecturecomponents.notesList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sabag.ronen.refactortonewarchitecturecomponents.R;
import com.sabag.ronen.refactortonewarchitecturecomponents.storage.NoteData;

import java.util.ArrayList;
import java.util.List;

class NotesAdapter extends RecyclerView.Adapter<NoteViewHolder>
        implements NoteViewHolder.OnViewHolderClick {

    private final OnNoteClickListener mListener;
    private List<NoteData> mNotes;

    NotesAdapter(OnNoteClickListener listener) {
        mNotes = new ArrayList<>();
        mListener = listener;
    }

    void setNotes(List<NoteData> notes) {
        mNotes = notes;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        holder.setText(mNotes.get(position).getNote());
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    @Override
    public void onViewHolderClick(int adapterPosition) {
        mListener.onNoteClick(mNotes.get(adapterPosition).getId());
    }
}