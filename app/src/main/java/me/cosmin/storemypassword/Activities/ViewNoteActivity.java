package me.cosmin.storemypassword.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.cosmin.storemypassword.Adapters.CardsAdapter;
import me.cosmin.storemypassword.Adapters.CredentialsAdapter;
import me.cosmin.storemypassword.Adapters.ViewPagerAdapter;
import me.cosmin.storemypassword.Controllers.NoteController;
import me.cosmin.storemypassword.Fragments.CardsFragment;
import me.cosmin.storemypassword.Fragments.CredentialsFragment;
import me.cosmin.storemypassword.Models.Note;
import me.cosmin.storemypassword.R;
import me.cosmin.storemypassword.Views.CircleImageView;

public class ViewNoteActivity extends MyActivity {

    private Note mNote;

    private CardsAdapter mCardsAdapter;
    private CredentialsAdapter mCredentialsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mNote = intent.getParcelableExtra("note");

        setContentView(R.layout.activity_view_note);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mNote = NoteController.getNoteById(mNote.getRecordId());
        setData();
        mCardsAdapter.setCards(mNote.getCards());
        mCredentialsAdapter.setCredentials(mNote.getCredentials());
        mCardsAdapter.notifyDataSetChanged();
        mCredentialsAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.view_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Intent newActivity;
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_edit_note:
                newActivity = new Intent(this, EditNoteActivity.class);
                newActivity.putExtra("note", mNote);
                startActivity(newActivity);
                finish();
                return true;
            case R.id.action_add_card:
                newActivity = new Intent(this, EditCardActivity.class);
                newActivity.putExtra("note", mNote);
                startActivity(newActivity);
                return true;
            case R.id.action_add_login:
                newActivity = new Intent(this, EditCredentialActivity.class);
                newActivity.putExtra("note", mNote);
                startActivity(newActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Prepare the view by configuring layouts, toolbars and loading data
     */
    private void init() {
        setupToolbar(getString(R.string.view_note_activity_title), R.drawable.back_icon_white);
        setupTabs();
        loadData();
        setData();
    }

    private void setupTabs() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_note_tab_container);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CardsFragment(), getString(R.string.cards_tab_title));
        adapter.addFragment(new CredentialsFragment(), getString(R.string.logins_tab_title));
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        // Add icons to tabs
        tabLayout.getTabAt(0).setIcon(R.drawable.credit_card_icon_white);
        tabLayout.getTabAt(1).setIcon(R.drawable.account_icon_white);
    }

    private void loadData() {
        mCardsAdapter = new CardsAdapter(mNote.getCards(), this);
        mCredentialsAdapter = new CredentialsAdapter(mNote.getCredentials(), this);
    }

    private void setData() {
        TextView title = (TextView) findViewById(R.id.view_note_title);
        title.setText(mNote.title);
        TextView url = (TextView) findViewById(R.id.view_note_url);
        url.setText(mNote.url);
        CircleImageView color = (CircleImageView) findViewById(R.id.view_note_color);
        color.changeBackgroundColor(mNote.color);
        ImageView status = (ImageView) findViewById(R.id.view_note_url_status);
        if ( Note.UrlStatus.SAFE.name().equals(mNote.safe) ) {
            status.setBackground(getDrawable(R.drawable.check_icon_black));
        } else {
            status.setBackground(getDrawable(R.drawable.alert_icon_black));
        }
    }

    public void openUrl(View view) {
        if ( !Note.UrlStatus.SAFE.name().equals(mNote.safe) ) {
            new AlertDialog.Builder(this, R.style.MyAlertDialog)
                    .setIcon(R.drawable.alert_icon_black)
                    .setTitle("Unsecure link")
                    .setMessage("The link has not been checked and approved for malware. Continue?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            followLink();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        } else {
            followLink();
        }
    }

    private void followLink() {
        String url = mNote.url;
        if ( !url.startsWith("http://") && !url.startsWith("https://") ) {
            url = "http://" + url;
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    public CredentialsAdapter getCredentialsAdapter() {
        return mCredentialsAdapter;
    }

    public CardsAdapter getCardsAdapter() {
        return mCardsAdapter;
    }
}
