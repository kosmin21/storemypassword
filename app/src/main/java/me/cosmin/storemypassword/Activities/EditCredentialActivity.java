package me.cosmin.storemypassword.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import me.cosmin.storemypassword.Models.Credential;
import me.cosmin.storemypassword.Models.Note;
import me.cosmin.storemypassword.R;

public class EditCredentialActivity extends MyActivity {

    private EditText mUsername;
    private EditText mPassword;
    private Credential mCredential;
    private Note mNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mCredential = intent.getParcelableExtra("credential");
        if ( mCredential != null ) {
            mNote = mCredential.note;
        } else{
            mNote = intent.getParcelableExtra("note");
        }

        setContentView(R.layout.activity_edit_credential);
        init();
    }

    /**
     * Prepare the view by configuring layouts, toolbars and loading data
     */
    private void init() {
        String title = getString(R.string.edit_credential_toolbar_title_new);
        if ( mCredential != null ) {
            title = getString(R.string.edit_credential_toolbar_title);
        }
        setupToolbar(title, R.drawable.close_icon_white);
        prepareView();
        setData();
    }

    private void prepareView() {
        mUsername = (EditText) findViewById(R.id.edit_login_username);
        mPassword = (EditText) findViewById(R.id.edit_login_password);
    }

    private void setData() {
        if ( mCredential != null ) {
            mUsername.setText(mCredential.username);
            mPassword.setText(mCredential.password);
        }
    }

    @Override
    public void save() {
        if ( mCredential == null ) {
            mCredential = new Credential();
        }
        mCredential.username = mUsername.getText().toString();
        mCredential.password = mPassword.getText().toString();

        mNote.setId(mNote.getRecordId());
        mCredential.note = mNote;
        mCredential.save();
        Toast.makeText(this, getString(R.string.toast_record_saved), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void delete() {
        if ( mCredential != null ) {
            mCredential.setId(mCredential.getRecordId());
            mCredential.delete();
            Toast.makeText(this, getString(R.string.toast_record_deleted), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, getString(R.string.cannot_delete), Toast.LENGTH_SHORT).show();
        }
    }
}
