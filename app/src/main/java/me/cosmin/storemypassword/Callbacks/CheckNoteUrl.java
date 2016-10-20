package me.cosmin.storemypassword.Callbacks;

import android.util.Log;

import me.cosmin.storemypassword.API.SafeBrowsingAPI;
import me.cosmin.storemypassword.Models.Note;
import me.cosmin.storemypassword.Models.SafeBrowsingRequest;
import me.cosmin.storemypassword.Models.SafeBrowsingResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckNoteUrl implements Callback<SafeBrowsingResponse> {

    private final String BASE_URL = "https://safebrowsing.googleapis.com";
    private Note mNote;


    public CheckNoteUrl(Note note) {
        mNote = note;
    }

    public void run() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SafeBrowsingAPI safeBrowsingAPI = retrofit.create(SafeBrowsingAPI.class);
        Call<SafeBrowsingResponse> request = safeBrowsingAPI.createRequest(createRequest());
        request.enqueue(this);
    }

    private SafeBrowsingRequest createRequest() {
        // create client
        SafeBrowsingRequest.Client client = new SafeBrowsingRequest.Client("me.cosmin.storemypassword", "1.0.0");

        // create threat entries
        SafeBrowsingRequest.ThreatEntry threatEntry = new SafeBrowsingRequest.ThreatEntry(mNote.url);

        // create threat info
        SafeBrowsingRequest.ThreatInfo threatInfo = new SafeBrowsingRequest.ThreatInfo(
                new String[] {"MALWARE", "SOCIAL_ENGINEERING"},
                new String[] {"WINDOWS"},
                new String[] {"URL"},
                new SafeBrowsingRequest.ThreatEntry[] {threatEntry});

        SafeBrowsingRequest request = new SafeBrowsingRequest();
        request.setClient(client);
        request.setThreatInfo(threatInfo);

        return request;
    }

    @Override
    public void onResponse(Call<SafeBrowsingResponse> call, Response<SafeBrowsingResponse> response) {
        // if response is 200 mark note as safe
        if (response.isSuccessful()) {
            mNote.safe = Note.UrlStatus.SAFE.name();
        } else {
            mNote.safe = Note.UrlStatus.UNSAFE.name();
        }
        mNote.save();
    }

    @Override
    public void onFailure(Call<SafeBrowsingResponse> call, Throwable t) {
        Log.d(getClass().getName(), t.toString());
    }
}
