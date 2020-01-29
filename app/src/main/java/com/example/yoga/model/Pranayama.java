package com.example.yoga.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Pranayama implements Parcelable {

    String name, sanskritName, details, images, video;

    public Pranayama(){}

    protected Pranayama(Parcel in) {
        name = in.readString();
        sanskritName = in.readString();
        details = in.readString();
        images = in.readString();
        video = in.readString();
    }

    public static final Creator<Pranayama> CREATOR = new Creator<Pranayama>() {
        @Override
        public Pranayama createFromParcel(Parcel in) {
            return new Pranayama(in);
        }

        @Override
        public Pranayama[] newArray(int size) {
            return new Pranayama[size];
        }
    };

    public Pranayama(String name, String sanskritName, String details, String images, String video) {
        this.name = name;
        this.sanskritName = sanskritName;
        this.details = details;
        this.images = images;
        this.video = video;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSanskritName() {
        return sanskritName;
    }

    public void setSanskritName(String sanskritName) {
        this.sanskritName = sanskritName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getVideo(){
        return  video;
    }

    public void  setVideo(){
        this.video = video;
    }

    public static Creator<Pranayama> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(sanskritName);
        parcel.writeString(details);
        parcel.writeString(images);
        parcel.writeString(video);
    }
}
