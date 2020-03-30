package com.sadhana.yoga.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Pranayama implements Parcelable {

    String pranayamaName, pranayamaSanskritName, pranayamaDetails, pranayamaImage, pranayamaVideo;

    public Pranayama(){}

    public Pranayama(String pranayamaName, String pranayamaSanskritName, String pranayamaDetails, String pranayamaImage, String pranayamaVideo) {
        this.pranayamaName = pranayamaName;
        this.pranayamaSanskritName = pranayamaSanskritName;
        this.pranayamaDetails = pranayamaDetails;
        this.pranayamaImage = pranayamaImage;
        this.pranayamaVideo = pranayamaVideo;
    }

    protected Pranayama(Parcel in) {
        pranayamaName = in.readString();
        pranayamaSanskritName = in.readString();
        pranayamaDetails = in.readString();
        pranayamaImage = in.readString();
        pranayamaVideo = in.readString();
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

    public String getPranayamaName() {
        return pranayamaName;
    }

    public void setPranayamaName(String pranayamaName) {
        this.pranayamaName = pranayamaName;
    }

    public String getPranayamaSanskritName() {
        return pranayamaSanskritName;
    }

    public void setPranayamaSanskritName(String pranayamaSanskritName) {
        this.pranayamaSanskritName = pranayamaSanskritName;
    }

    public String getPranayamaDetails() {
        return pranayamaDetails;
    }

    public void setPranayamaDetails(String pranayamaDetails) {
        this.pranayamaDetails = pranayamaDetails;
    }

    public String getPranayamaImage() {
        return pranayamaImage;
    }

    public void setPranayamaImage(String pranayamaImage) {
        this.pranayamaImage = pranayamaImage;
    }

    public String getPranayamaVideo() {
        return pranayamaVideo;
    }

    public void setPranayamaVideo(String pranayamaVideo) {
        this.pranayamaVideo = pranayamaVideo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pranayamaName);
        dest.writeString(pranayamaSanskritName);
        dest.writeString(pranayamaDetails);
        dest.writeString(pranayamaImage);
        dest.writeString(pranayamaVideo);
    }
}
