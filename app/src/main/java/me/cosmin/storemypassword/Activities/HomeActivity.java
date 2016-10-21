package me.cosmin.storemypassword.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import me.cosmin.storemypassword.Adapters.NotesAdapter;
import me.cosmin.storemypassword.Controllers.LoginController;
import me.cosmin.storemypassword.Controllers.NoteController;
import me.cosmin.storemypassword.R;
import me.cosmin.storemypassword.Views.MyRecyclerView;

public class HomeActivity extends MyActivity {

    private NotesAdapter mNotesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNotesAdapter.setNotes(NoteController.getAllNotes());
        mNotesAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_lock:
                new LoginController(this).logOut();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Prepare the view by configuring layouts, toolbars and loading data
     */
    private void init() {
        setupToolbar(getString(R.string.activity_home_title));
        loadData();
        setData();
    }

    /**
     * OnClick action for floating action button
     * @param view
     */
    public void addNote(View view) {
        Intent newNote = new Intent(this, EditNoteActivity.class);
        startActivity(newNote);
    }

    private void loadData() {
        mNotesAdapter = new NotesAdapter(NoteController.getAllNotes(), this);
    }

    private void setData() {
        MyRecyclerView recyclerView = (MyRecyclerView) findViewById(R.id.note_gridview);
        recyclerView.setHasFixedSize(true);
        recyclerView.addGridLayout(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        recyclerView.addItemSpacing();

        recyclerView.setAdapter(mNotesAdapter);
    }
}
