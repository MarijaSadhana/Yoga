package com.example.yoga.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Programs implements Parcelable {

    String programTitle, programImage;

    public Programs(String programTitle, String programImage) {
        this.programTitle = programTitle;
        this.programImage = programImage;
    }

    protected Programs(Parcel in) {
        programTitle = in.readString();
        programImage = in.readString();
    }

    public static final Creator<Programs> CREATOR = new Creator<Programs>() {
        @Override
        public Programs createFromParcel(Parcel in) {
            return new Programs(in);
        }

        @Override
        public Programs[] newArray(int size) {
            return new Programs[size];
        }
    };

    public String getProgramTitle() {
        return programTitle;
    }

    public void setProgramTitle(String programTitle) {
        this.programTitle = programTitle;
    }

    public String getProgramImage() {
        return programImage;
    }

    public void setProgramImage(String programImage) {
        this.programImage = programImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(programTitle);
        dest.writeString(programImage);
    }
}
