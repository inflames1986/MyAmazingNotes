package com.inflames1986.myamazingnotes.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.StringRes;

public class Note implements Parcelable {

    @StringRes
    private final int noteName;

    @StringRes
    private final int noteDesc;

    @StringRes
    private final int noteDate;

    public Note(int noteName, int noteDesc, int noteDate) {
        this.noteName = noteName;
        this.noteDesc = noteDesc;
        this.noteDate = noteDate;
    }

    protected Note(Parcel in) {
        noteName = in.readInt();
        noteDesc = in.readInt();
        noteDate = in.readInt();
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

    public int getNoteName() {
        return noteName;
    }

    public int getNoteDesc() {
        return noteDesc;
    }

    public int getNoteDate() {
        return noteDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(noteName);
        parcel.writeInt(noteDesc);
        parcel.writeInt(noteDate);
    }
}
