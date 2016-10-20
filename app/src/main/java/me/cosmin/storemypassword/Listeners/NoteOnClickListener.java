package me.cosmin.storemypassword.Listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import me.cosmin.storemypassword.Activities.ViewNoteActivity;
import me.cosmin.storemypassword.Models.Note;

public class NoteOnClickListener implements View.OnClickListener {

    private Note mNote;
    private Context mContext;

    public NoteOnClickListener(Context context, Note note) {
        mNote = note;
        mContext = context;
    }

    @Override
    public void onClick(View view) {
        Intent viewNote = new Intent(mContext, ViewNoteActivity.class);
        if ( mNote != null ) {
            viewNote.putExtra("note", mNote);
        }
        mContext.startActivity(viewNote);
    }
}
