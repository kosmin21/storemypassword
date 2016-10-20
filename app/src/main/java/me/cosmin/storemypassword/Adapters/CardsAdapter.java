package me.cosmin.storemypassword.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import me.cosmin.storemypassword.Activities.EditCardActivity;
import me.cosmin.storemypassword.Helpers.CardHelper;
import me.cosmin.storemypassword.Models.Card;
import me.cosmin.storemypassword.R;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.ViewHolder> {

    private List<Card> mCards;
    private Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView number;
        public TextView name;
        public TextView cvv;
        public TextView pin;
        public TextView valid;
        public TextView password;
        public TextView account;
        public TextView sortCode;
        public ImageView type;
        public View editButton;

        public ViewHolder(View v) {
            super(v);
            number = (TextView) v.findViewById(R.id.card_number);
            name = (TextView) v.findViewById(R.id.card_name);
            cvv = (TextView) v.findViewById(R.id.card_cvv);
            pin = (TextView) v.findViewById(R.id.card_pin);
            valid = (TextView) v.findViewById(R.id.card_valid);
            password = (TextView) v.findViewById(R.id.card_password);
            account = (TextView) v.findViewById(R.id.card_account);
            sortCode = (TextView) v.findViewById(R.id.card_sort_code);
            type = (ImageView) v.findViewById(R.id.card_type);
            editButton = v.findViewById(R.id.edit_card_button);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CardsAdapter(List<Card> cards, Context context) {
        mCards = cards;
        mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CardsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_note_details_card_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Card card = mCards.get(position);
        int imgResource = mContext.getResources().getIdentifier(card.type, "drawable", mContext.getPackageName());
        Drawable cardType = mContext.getDrawable(imgResource);
        holder.type.setImageDrawable(cardType);
        holder.number.setText(CardHelper.formatCardNumber(card.number));
        holder.name.setText(card.name);
        holder.cvv.setText(card.cvv);
        holder.pin.setText(card.pin);
        holder.password.setText(card.password);
        holder.account.setText(card.account);
        holder.sortCode.setText(CardHelper.formatSortCode(card.sortCode));
        holder.valid.setText(CardHelper.formatDate(card.validFrom) + " to " + CardHelper.formatDate(card.validUntil));

        View.OnClickListener clickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, EditCardActivity.class);
                i.putExtra("card", card);
                mContext.startActivity(i);
            }
        };

        holder.editButton.setOnClickListener(clickListener);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mCards.size();
    }

    public void setCards(List<Card> cards) {
        mCards = cards;
    }
}
