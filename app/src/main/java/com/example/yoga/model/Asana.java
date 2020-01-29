package com.example.yoga.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Asana implements Parcelable {

    String name, sanskritName, details, images, asanaCategory;

    public Asana() {}

    public Asana(String name, String sanskritName, String details, String images, String asanaCategory) {
        this.name = name;
        this.sanskritName = sanskritName;
        this.details = details;
        this.images = images;
        this.asanaCategory = asanaCategory;
    }

    protected Asana(Parcel in) {
        name = in.readString();
        sanskritName = in.readString();
        details = in.readString();
        images = in.readString();
    }

    public static final Creator<Asana> CREATOR = new Creator<Asana>() {
        @Override
        public Asana createFromParcel(Parcel in) {
            return new Asana(in);
        }

        @Override
        public Asana[] newArray(int size) {
            return new Asana[size];
        }
    };

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

    public String getAsanaCategory() {return asanaCategory;}

    public void setAsanaCategory(String asanaCategory) {this.asanaCategory = asanaCategory;}

    public static Creator<Asana> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(sanskritName);
        dest.writeString(details);
        dest.writeString(images);
    }
}

