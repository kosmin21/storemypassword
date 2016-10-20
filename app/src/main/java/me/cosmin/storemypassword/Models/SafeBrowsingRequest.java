package me.cosmin.storemypassword.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SafeBrowsingRequest {

    public SafeBrowsingRequest() {
    }

    public static class Client {
        @SerializedName("clientId")
        @Expose
        private String mClientId;
        @SerializedName("clientVersion")
        @Expose
        private String mClientVersion;

        public Client(String clientId, String clientVersion) {
            mClientId = clientId;
            mClientVersion = clientVersion;
        }
    }

    public static class ThreatInfo {
        @SerializedName("threatTypes")
        @Expose
        private String[] mThreatTypes;// = new ArrayList<String>();
        @SerializedName("platformTypes")
        @Expose
        private String[] mPlatformTypes;
        @SerializedName("threatEntryTypes")
        @Expose
        private String[] mThreatEntryTypes;
        @SerializedName("threatEntries")
        @Expose
        private ThreatEntry[] mThreatEntries;

        public ThreatInfo(String[] threatTypes, String[] platformTypes, String[] threatEntryTypes, ThreatEntry[] threatEntries) {
            mThreatTypes = threatTypes;
            mPlatformTypes = platformTypes;
            mThreatEntryTypes = threatEntryTypes;
            mThreatEntries = threatEntries;
        }

        public void setThreatTypes(String[] threatTypes) {
            mThreatTypes = threatTypes;
        }

        public void setPlatformTypes(String[] platformTypes) {
            platformTypes = platformTypes;
        }

        public void setThreatEntryTypes(String[] threatEntryTypes) {
            threatEntryTypes = threatEntryTypes;
        }

        public void setThreatEntries(ThreatEntry[] threatEntries) {
            mThreatEntries = threatEntries;
        }
    }

    public static class ThreatEntry {

        @SerializedName("url")
        @Expose
        private String mUrl;

        public ThreatEntry(String url) {
            mUrl = url;
        }
    }

    @SerializedName("client")
    @Expose
    private Client mClient;
    @SerializedName("threatInfo")
    @Expose
    private ThreatInfo mThreatInfo;

    public void setClient(Client client) {
        this.mClient = client;
    }

    public void setThreatInfo(ThreatInfo threatInfo) {
        mThreatInfo = threatInfo;
    }

}
