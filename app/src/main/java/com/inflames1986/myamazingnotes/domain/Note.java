package com.inflames1986.myamazingnotes.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.StringRes;

public class Note implements Parcelable {

    public Note(int title, int image, int desc, int date) {
        this.title = title;
        this.image = image;
        this.desc = desc;
        this.date = date;
    }

    public int getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public int getDesc() {
        return desc;
    }

    public int getDate() {
        return date;
    }

    @StringRes
    private final int title;

    @StringRes
    private final int image;

    @StringRes
    private final int desc;

    @StringRes
    private final int date;


    protected Note(Parcel in) {
        title = in.readInt();
        image = in.readInt();
        desc = in.readInt();
        date = in.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(title);
        parcel.writeInt(image);
        parcel.writeInt(desc);
        parcel.writeInt(date);
    }
}
