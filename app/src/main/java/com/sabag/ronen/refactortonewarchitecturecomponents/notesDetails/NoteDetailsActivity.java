package com.sabag.ronen.refactortonewarchitecturecomponents.notesDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.sabag.ronen.refactortonewarchitecturecomponents.App;
import com.sabag.ronen.refactortonewarchitecturecomponents.R;

public class NoteDetailsActivity extends AppCompatActivity
        implements View.OnClickListener, NoteDetailsContract.View {

    private long mNoteId;
    private EditText mNoteEditView;
    private NoteDetailsPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        mNoteId = getIntent().getLongExtra("NOTE_ID", -1);

        mNoteEditView = findViewById(R.id.noteText);
        findViewById(R.id.delete).setOnClickListener(this);
        findViewById(R.id.update).setOnClickListener(this);

        NoteDetailsModel model = new NoteDetailsModel(((App)getApplication()).getNotesTable());
        mPresenter = new NoteDetailsPresenter(this, model);
        mPresenter.loadData(mNoteId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete:
                mPresenter.deleteNoteClick(mNoteId);
                break;
            case R.id.update:
                mPresenter.updateNoteClick(mNoteId, mNoteEditView.getText().toString());
                break;
        }
    }

    @Override
    public void showNote(String note) {
        mNoteEditView.setText(note);
    }

    @Override
    public void close() {
        finish();
    }
}