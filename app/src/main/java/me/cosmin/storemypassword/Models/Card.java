package me.cosmin.storemypassword.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

public class Card extends SugarRecord implements Parcelable {

    public Note note;
    public String type;
    public String number;
    public String name;
    public String cvv;
    public String password;
    public String pin;
    public String validFrom;
    public String validUntil;
    public String account;
    public String sortCode;

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

    public Card() {
    }

    public Card(Note note, String type, String number, String name, String cvv, String password, String pin, String validFro, String validUntil, String account, String sortCode) {
        this.note = note;
        this.type = type;
        this.number = number;
        this.name = name;
        this.cvv = cvv;
        this.password = password;
        this.pin = pin;
        validFrom = validFro;
        this.validUntil = validUntil;
        this.account = account;
        this.sortCode = sortCode;
    }

    protected Card(Parcel in) {
        note = in.readParcelable(Note.class.getClassLoader());
        type = in.readString();
        number = in.readString();
        name = in.readString();
        cvv = in.readString();
        password = in.readString();
        pin = in.readString();
        validFrom = in.readString();
        validUntil = in.readString();
        account = in.readString();
        sortCode = in.readString();
        mRecordId = in.readLong();
    }

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(note, flags);
        dest.writeString(type);
        dest.writeString(number);
        dest.writeString(name);
        dest.writeString(cvv);
        dest.writeString(password);
        dest.writeString(pin);
        dest.writeString(validFrom);
        dest.writeString(validUntil);
        dest.writeString(account);
        dest.writeString(sortCode);
        dest.writeLong(mRecordId);
    }

    public void setRecordId(long recordId) {
        mRecordId = recordId;
    }
}
