package com.sadhana.yoga.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Asanas implements Parcelable {

    String asanaName, sanskritName, asanaDetails, asanaCategory, asanaImages;

    public Asanas(String asanaName, String sanskritName, String asanaDetails, String asanaImages, String asanaCategory) {
        this.asanaName = asanaName;
        this.sanskritName = sanskritName;
        this.asanaDetails = asanaDetails;
        this.asanaImages = asanaImages;
        this.asanaCategory = asanaCategory;
    }

    protected Asanas(Parcel in) {
        asanaName = in.readString();
        sanskritName = in.readString();
        asanaDetails = in.readString();
        asanaCategory = in.readString();
        asanaImages = in.readString();
    }

    public static final Creator<Asanas> CREATOR = new Creator<Asanas>() {
        @Override
        public Asanas createFromParcel(Parcel in) {
            return new Asanas(in);
        }

        @Override
        public Asanas[] newArray(int size) {
            return new Asanas[size];
        }
    };

    public String getAsanaName() {
        return asanaName;
    }

    public void setAsanaName(String asanaName) {
        this.asanaName = asanaName;
    }

    public String getSanskritName() {
        return sanskritName;
    }

    public void setSanskritName(String sanskritName) {
        this.sanskritName = sanskritName;
    }

    public String getAsanaDetails() {
        return asanaDetails;
    }

    public void setAsanaDetails(String asanaDetails) {
        this.asanaDetails = asanaDetails;
    }

    public String getAsanaImages() {
        return asanaImages;
    }

    public void setAsanaImages(String asanaImages) {
        this.asanaImages = asanaImages;
    }

    public String getAsanaCategory() {
        return asanaCategory;
    }

    public void setAsanaCategory(String asanaCategory) {
        this.asanaCategory = asanaCategory;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(asanaName);
        dest.writeString(sanskritName);
        dest.writeString(asanaDetails);
        dest.writeString(asanaCategory);
        dest.writeString(asanaImages);
    }
}

