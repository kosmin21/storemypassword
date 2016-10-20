package me.cosmin.storemypassword.API;

import me.cosmin.storemypassword.Models.SafeBrowsingRequest;
import me.cosmin.storemypassword.Models.SafeBrowsingResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SafeBrowsingAPI {

    String API_KEY = "AIzaSyDIAAE6Y1ugCcbFJrHG8dMkbOziMyUnM0U";

    @POST("v4/threatMatches:find?key=" + API_KEY)
    Call<SafeBrowsingResponse> createRequest(@Body SafeBrowsingRequest safeBrowsingRequest);
}
