package me.cosmin.storemypassword.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SafeBrowsingResponse {

    private Match[] mMatches;

    public void setMatches(Match[] matches) {
        mMatches = matches;
    }

    public class Match {

        @SerializedName("threatType")
        @Expose
        private String mThreatType;
        @SerializedName("platformType")
        @Expose
        private String mPlatformType;
        @SerializedName("threatEntryType")
        @Expose
        private String mThreatEntryType;
        @SerializedName("threat")
        @Expose
        private Threat[] mThreat;
        @SerializedName("threatMetaData")
        @Expose
        private ThreatMetaData[] mThreatMetaData;
        @SerializedName("cacheDuration")
        @Expose
        private String mCacheDuration;

        public void setThreatType(String threatType) {
            mThreatType = threatType;
        }

        public void setPlatformType(String platformType) {
            mPlatformType = platformType;
        }

        public void setThreatEntryType(String threatEntryType) {
            mThreatEntryType = threatEntryType;
        }

        public void setThreat(Threat[] threat) {
            mThreat = threat;
        }

        public void setThreatMetaData(ThreatMetaData[] threatMetaData) {
            mThreatMetaData = threatMetaData;
        }

        public void setCacheDuration(String cacheDuration) {
            mCacheDuration = cacheDuration;
        }
    }

    public class Threat {
        @SerializedName("url")
        @Expose
        private String mUrl;

        public void setUrl(String url) {
            mUrl = url;
        }
    }

    public class ThreatMetaData {
        @SerializedName("entry")
        @Expose
        private Entry[] mEntries;

        public void setEntries(Entry[] entries) {
            mEntries = entries;
        }
    }

    public class Entry {
        @SerializedName("key")
        @Expose
        private String mKey;
        @SerializedName("value")
        @Expose
        private String mValue;

        public void setKey(String key) {
            mKey = key;
        }

        public void setValue(String value) {
            mValue = value;
        }
    }
}
