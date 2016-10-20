package me.cosmin.storemypassword.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.cosmin.storemypassword.Activities.EditCredentialActivity;
import me.cosmin.storemypassword.Models.Credential;
import me.cosmin.storemypassword.R;

public class CredentialsAdapter extends RecyclerView.Adapter<CredentialsAdapter.ViewHolder> {

    private List<Credential> mCredentials;
    private Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView username;
        public TextView password;
        public View editButton;

        public ViewHolder(View v) {
            super(v);
            username = (TextView) v.findViewById(R.id.view_login_username);
            password = (TextView) v.findViewById(R.id.view_login_password);
            editButton = v.findViewById(R.id.edit_login_button);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CredentialsAdapter(List<Credential> credentials, Context context) {
        mCredentials = credentials;
        mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CredentialsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_note_details_credential_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Credential credential = mCredentials.get(position);
        holder.username.setText(credential.username);
        holder.password.setText(credential.password);

        View.OnClickListener clickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, EditCredentialActivity.class);
                i.putExtra("credential", credential);
                mContext.startActivity(i);
            }
        };

        holder.editButton.setOnClickListener(clickListener);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mCredentials.size();
    }

    public void setCredentials(List<Credential> credentials) {
        mCredentials = credentials;
    }
}
