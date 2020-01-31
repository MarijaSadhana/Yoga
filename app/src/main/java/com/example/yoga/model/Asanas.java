package com.example.yoga.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Asanas {

    String asanaName, sanskritName, asanaDetails, asanaImages, asanaCategory;

    public Asanas(String asanaName, String sanskritName, String asanaDetails, String asanaImages, String asanaCategory) {
        this.asanaName = asanaName;
        this.sanskritName = sanskritName;
        this.asanaDetails = asanaDetails;
        this.asanaImages = asanaImages;
        this.asanaCategory = asanaCategory;
    }

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
}

