package com.sabag.ronen.refactortonewarchitecturecomponents.notesList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sabag.ronen.refactortonewarchitecturecomponents.R;

class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    interface OnViewHolderClick {
        void onViewHolderClick(int adapterPosition);
    }

    private final TextView mNoteView;
    private final OnViewHolderClick mListener;

    NoteViewHolder(View itemView, OnViewHolderClick listener) {
        super(itemView);
        mNoteView = itemView.findViewById(R.id.note);
        itemView.setOnClickListener(this);
        mListener = listener;
    }

    public void setText(String note) {
        mNoteView.setText(note);
    }

    @Override
    public void onClick(View v) {
        mListener.onViewHolderClick(getAdapterPosition());
    }
}