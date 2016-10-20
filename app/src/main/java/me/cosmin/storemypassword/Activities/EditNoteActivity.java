package me.cosmin.storemypassword.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import me.cosmin.storemypassword.Adapters.ColorsAdapter;
import me.cosmin.storemypassword.Controllers.NoteController;
import me.cosmin.storemypassword.Models.Note;
import me.cosmin.storemypassword.R;
import me.cosmin.storemypassword.StaticLists.Colors;
import me.cosmin.storemypassword.Views.CircleImageView;
import me.cosmin.storemypassword.Views.MyRecyclerView;

public class EditNoteActivity extends MyActivity {

    private Note mNote;

    private EditText mTitle;
    private EditText mUrl;
    private CircleImageView mColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mNote = intent.getParcelableExtra("note");

        setContentView(R.layout.activity_edit_note);
        init();
    }

    /**
     * Prepare the view by configuring layouts, toolbars and loading data
     */
    private void init() {
        String title = getString(R.string.edit_note_toolbar_title_new);
        if ( mNote != null ) {
            title = getString(R.string.edit_note_toolbar_title);
        }
        setupToolbar(title, R.drawable.close_icon_white);
        prepareView();
        setData();
    }

    private void prepareView() {
        mTitle = (EditText) findViewById(R.id.note_title);
        mUrl = (EditText) findViewById(R.id.note_url);
        mColor = (CircleImageView) findViewById(R.id.note_color);

        MyRecyclerView recyclerView = (MyRecyclerView) findViewById(R.id.note_colors);
        recyclerView.setHasFixedSize(true);
        recyclerView.addGridLayout(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        recyclerView.addItemSpacing();

        ColorsAdapter colorsAdapter = new ColorsAdapter(Colors.COLOR_OPTIONS);
        colorsAdapter.setColorSelectedListener(new ColorsAdapter.ColorClickListener() {
            @Override
            public void onColorSelected(String color) {
                setColor(color);
            }
        });

        recyclerView.setAdapter(colorsAdapter);
    }

    private void setData() {
        if( mNote == null ) {
            mNote = new Note();
            setColor(Colors.DEFAULT_COLOR);
        }
        mTitle.setText(mNote.title);
        mUrl.setText(mNote.url);
        setColor(mNote.color);
    }

    private boolean validateFields() {
        if ( mTitle.length() < 1 ) {
            mTitle.hasFocus();
            mTitle.setError(getString(R.string.error_required_title));
            return false;
        }

        if ( !Patterns.WEB_URL.matcher(mUrl.getText().toString()).matches() ) {
            mUrl.hasFocus();
            mUrl.setError(getString(R.string.error_invalid_url));
            return false;
        }

        return true;
    }

    @Override
    public void save() {
        if ( validateFields() ) {
            mNote.title = mTitle.getText().toString();
            mNote.url = mUrl.getText().toString();
            mNote.checkUrlAndSave();
            finish();
        }
    }

    @Override
    public void delete() {
        if ( mNote != null ) {
            mNote.setId(mNote.getRecordId());
            NoteController.deleteNote(mNote);
            Intent home = new Intent(this, HomeActivity.class);
            startActivity(home);
        } else {
            Toast.makeText(this, getString(R.string.cannot_delete), Toast.LENGTH_SHORT).show();
        }
    }

    private void setColor(String color) {
        mNote.color = color;
        mColor.changeBackgroundColor(color);
    }
}
