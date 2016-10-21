package me.cosmin.storemypassword.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.cosmin.storemypassword.Activities.ViewNoteActivity;
import me.cosmin.storemypassword.Adapters.CredentialsAdapter;
import me.cosmin.storemypassword.R;
import me.cosmin.storemypassword.Views.VerticalSpaceItemDecoration;

public class CredentialsFragment extends Fragment {

    public CredentialsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_credentials, container, false);
        loadData(view);
        return view;
    }

    private void loadData(View currentView) {
        ViewNoteActivity activity = (ViewNoteActivity) getActivity();
        CredentialsAdapter credentialsAdapter = activity.getCredentialsAdapter();
        if ( currentView != null ) {
            RecyclerView recyclerView = (RecyclerView) currentView.findViewById(R.id.view_note_logins);
            recyclerView.setHasFixedSize(true);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            int space = (int) getResources().getDimension(R.dimen.recycler_view_spacing);
            recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(space));

            recyclerView.setAdapter(credentialsAdapter);
        }
    }
}
