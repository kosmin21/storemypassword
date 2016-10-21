package me.cosmin.storemypassword.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.List;

import me.cosmin.storemypassword.Callbacks.CheckNoteUrl;

public class Note extends SugarRecord implements Parcelable {

    public String title;
    public String url;
    public String safe = UrlStatus.NOT_CHECKED.name();
    public String color;

    public enum UrlStatus {
        SAFE, UNSAFE, NOT_CHECKED
    }

    // Used to store one-to-many relationships
    @Ignore
    private List<Credential> mCredentials;
    @Ignore
    private List<Card> mCards;
    @Ignore
    private long mRecordId;
    @Ignore
    public static final long INVALID = 0;

    public Note() {
    }

    public long checkUrlAndSave() {
        CheckNoteUrl checkUrl = new CheckNoteUrl(this);
        checkUrl.run();
        return saveWithId();
    }

    @Override
    public long save() {
        return saveWithId();
    }

    private long saveWithId() {
        if ( mRecordId > INVALID ) {
            setId(mRecordId);
        }
        return super.save();
    }

    public Note(String title, String url, String color) {
        this.title = title;
        this.url = url;
        this.safe = UrlStatus.NOT_CHECKED.name();
        this.color = color;
    }

    protected Note(Parcel in) {
        title = in.readString();
        url = in.readString();
        safe = in.readString();
        color = in.readString();
        mCredentials = in.createTypedArrayList(Credential.CREATOR);
        mCards = in.createTypedArrayList(Card.CREATOR);
        mRecordId = in.readLong();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public void setCredentials(List<Credential> loginDetailms) {
        mCredentials = loginDetailms;
    }

    public void setCards(List<Card> cards) {
        mCards = cards;
    }

    public List<Credential> getCredentials() {
        return mCredentials;
    }

    public List<Card> getCards() {
        return mCards;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(url);
        dest.writeString(safe);
        dest.writeString(color);
        dest.writeTypedList(mCredentials);
        dest.writeTypedList(mCards);
        dest.writeLong(mRecordId);
    }

    public void setRecordId(long recordId) {
        mRecordId = recordId;
    }

    public long getRecordId() {
        return mRecordId;
    }
}
