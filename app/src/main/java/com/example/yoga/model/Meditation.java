package com.example.yoga.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Meditation implements Parcelable {

    String title;
    String image;

    public Meditation(){}

    protected Meditation(Parcel in) {
        title = in.readString();
        image = in.readString();
    }

    public static final Creator<Meditation> CREATOR = new Creator<Meditation>() {
        @Override
        public Meditation createFromParcel(Parcel in) {
            return new Meditation(in);
        }

        @Override
        public Meditation[] newArray(int size) {
            return new Meditation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(image);
    }

    public Meditation(String title, String audio) {
        this.title = title;
        this.image = audio;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String audio) {
        this.image = audio;
    }
}
