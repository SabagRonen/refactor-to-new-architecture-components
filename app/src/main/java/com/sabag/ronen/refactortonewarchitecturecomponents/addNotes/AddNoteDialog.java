package com.sabag.ronen.refactortonewarchitecturecomponents.addNotes;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.sabag.ronen.refactortonewarchitecturecomponents.R;

public class AddNoteDialog extends DialogFragment implements View.OnClickListener {

    private EditText mNoteEditTextView;
    private AddNoteListener mAddNoteListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof AddNoteListener)) {
            throw new IllegalArgumentException("Expected that the host activity will implement AddNoteListener");
        }
        mAddNoteListener = (AddNoteListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_note_dialog, container);
        mNoteEditTextView = view.findViewById(R.id.noteText);
        view.findViewById(R.id.createNote).setOnClickListener(this);
        view.findViewById(R.id.cancelNote).setOnClickListener(this);
        return view;
    }

    public void onResume() {
        super.onResume();
        adjustWidth();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createNote:
                mAddNoteListener.onNoteAdded(mNoteEditTextView.getText().toString());
                break;
        }
        dismiss();
    }

    private void adjustWidth() {
        Window window = getDialog().getWindow();
        if (window != null) {
            Point size = new Point();
            Display display = window.getWindowManager().getDefaultDisplay();
            display.getSize(size);
            window.setLayout((int) (size.x * 0.75), WindowManager.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.CENTER);
        }
    }
}