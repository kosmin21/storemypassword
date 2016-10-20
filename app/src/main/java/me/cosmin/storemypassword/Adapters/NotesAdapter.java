package me.cosmin.storemypassword.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.cosmin.storemypassword.Listeners.NoteOnClickListener;
import me.cosmin.storemypassword.Models.Note;
import me.cosmin.storemypassword.R;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Note> mNotes;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View box;
        public TextView title;
        public TextView cards;
        public TextView logins;
        public ViewHolder(View v) {
            super(v);
            box = v.findViewById(R.id.preview_box);
            title = (TextView) v.findViewById(R.id.preview_item_title);
            cards = (TextView) v.findViewById(R.id.preview_item_cards);
            logins = (TextView) v.findViewById(R.id.preview_item_logins);
        }
    }

    public NotesAdapter(List<Note> notes, Context context) {
        mNotes = notes;
        mContext = context;
    }

    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home_note_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(NotesAdapter.ViewHolder holder, int position) {
        Note note = mNotes.get(position);
        GradientDrawable bg = (GradientDrawable) mContext.getResources().getDrawable(R.drawable.note_shape, mContext.getTheme());
        if ( bg != null && note.color != null && !note.color.isEmpty()  ) {
            bg.setColor(Color.parseColor(note.color));
            holder.box.setBackground(bg);
        }
        holder.title.setText(note.title);
        if ( note.getCards().size() > 0 ) {
            holder.cards.setText(note.getCards().size() + "");
        }
        if ( note.getCredentials().size() > 0 ) {
            holder.logins.setText(note.getCredentials().size() + "");
        }
        holder.box.setOnClickListener(new NoteOnClickListener(mContext, note));
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public void setNotes(List<Note> notes) {
        mNotes = notes;
    }

    public List<Note> getNotes() {
        return mNotes;
    }
}
