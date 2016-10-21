package me.cosmin.storemypassword.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;

import me.cosmin.storemypassword.Models.Card;
import me.cosmin.storemypassword.R;

public class EditCardActivity extends MyActivity {

    private EditText mCardNumberFirstGroup;
    private EditText mCardNumberSecondGroup;
    private EditText mCardNumberThirdGroup;
    private EditText mCardNumberFourthGroup;

    private EditText mCardName;
    private EditText mCardPin;
    private EditText mCardCVV;
    private EditText mCardPassword;
    private EditText mCardAccount;
    private EditText mCardSortCodeFirstGroup;
    private EditText mCardSortCodeSecondGroup;
    private EditText mCardSortCodeThirdGroup;

    private Card mCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mCard = intent.getParcelableExtra("card");

        setContentView(R.layout.activity_edit_card);
        prepareView();
    }

    private void prepareView() {
        setupToolbar();
        loadFields();
        // If card already exists prepopulate fields for edit purpose
        if( mCard != null ) {
            prePopulateFields();
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.edit_card_toolbar);
        toolbar.setNavigationIcon(R.drawable.close_icon_white);
        if ( mCard != null ) {
            toolbar.setTitle("Edit Card");
        } else {
            toolbar.setTitle("New Card");
        }
        setSupportActionBar(toolbar);
    }

    private void loadFields() {
        mCardNumberFirstGroup = (EditText) findViewById(R.id.edit_card_number_first_group);
        mCardNumberSecondGroup = (EditText) findViewById(R.id.edit_card_number_second_group);
        mCardNumberThirdGroup = (EditText) findViewById(R.id.edit_card_number_third_group);
        mCardNumberFourthGroup = (EditText) findViewById(R.id.edit_card_number_fourth_group);
        mCardName = (EditText) findViewById(R.id.edit_card_name);
        mCardCVV = (EditText) findViewById(R.id.edit_card_cvv);
        mCardPin = (EditText) findViewById(R.id.edit_card_pin);
        mCardPassword = (EditText) findViewById(R.id.edit_card_password);
        mCardAccount = (EditText) findViewById(R.id.edit_card_account);
        mCardSortCodeFirstGroup = (EditText) findViewById(R.id.edit_card_sort_code_first_group);
        mCardSortCodeSecondGroup = (EditText) findViewById(R.id.edit_card_sort_code_second_group);
        mCardSortCodeThirdGroup = (EditText) findViewById(R.id.edit_card_sort_code_third_group);
    }

    private void prePopulateFields() {
        mCardNumberFirstGroup.setText(mCard.number.substring(0,4));
        mCardNumberSecondGroup.setText(mCard.number.substring(4,8));
        mCardNumberThirdGroup.setText(mCard.number.substring(8,12));
        mCardNumberFourthGroup.setText(mCard.number.substring(12,16));
        mCardName.setText(mCard.name);
        mCardCVV.setText(mCard.cvv);
        mCardPin.setText(mCard.pin);
        mCardPassword.setText(mCard.password);
        mCardAccount.setText(mCard.account);
        mCardSortCodeFirstGroup.setText(mCard.sortCode.substring(0,2));
        mCardSortCodeSecondGroup.setText(mCard.sortCode.substring(2,4));
        mCardSortCodeThirdGroup.setText(mCard.sortCode.substring(4,6));
    }

    @Override
    public void save() {
        Toast.makeText(this, getString(R.string.toast_not_implemented), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void delete() {
        Toast.makeText(this, getString(R.string.toast_not_implemented), Toast.LENGTH_SHORT).show();
    }
}
