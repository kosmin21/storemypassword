package me.cosmin.storemypassword.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

public class Credential extends SugarRecord implements Parcelable {

    public Note note;
    public String username;
    public String password;

    @Ignore
    private long mRecordId;
    @Ignore
    public static final long INVALID = 0;

    @Override
    public long save() {
        if ( mRecordId > INVALID ) {
            setId(mRecordId);
        }
        return super.save();
    }

    public Credential() {
    }

    public Credential(Note note, String username, String password) {
        this.note = note;
        this.username = username;
        this.password = password;
    }

    protected Credential(Parcel in) {
        note = in.readParcelable(Note.class.getClassLoader());
        username = in.readString();
        password = in.readString();
        mRecordId = in.readLong();
    }

    public static final Creator<Credential> CREATOR = new Creator<Credential>() {
        @Override
        public Credential createFromParcel(Parcel in) {
            return new Credential(in);
        }

        @Override
        public Credential[] newArray(int size) {
            return new Credential[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(note, flags);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeLong(mRecordId);
    }

    public void setRecordId(long recordId) {
        mRecordId = recordId;
    }

    public Long getRecordId() {
        return mRecordId;
    }
}
