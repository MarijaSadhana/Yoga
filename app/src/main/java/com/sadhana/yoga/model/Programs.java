package com.sadhana.yoga.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Programs implements Parcelable {

    String programTitle, programImage, programVideo, duration, programDescription;

    public Programs(String programTitle, String programImage, String programVideo, String duration, String programDescription) {
        this.programTitle = programTitle;
        this.programImage = programImage;
        this.programVideo = programVideo;
        this.duration = duration;
        this.programDescription = programDescription;
    }

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

    public String getProgramVideo() {
        return programVideo;
    }

    public void setProgramVideo(String programVideo) {
        this.programVideo = programVideo;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getProgramDescription() {
        return programDescription;
    }

    public void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    public static Creator<Programs> getCREATOR() {
        return CREATOR;
    }

    protected Programs(Parcel in) {
        programTitle = in.readString();
        programImage = in.readString();
        programVideo = in.readString();
        duration = in.readString();
        programDescription = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(programTitle);
        dest.writeString(programImage);
        dest.writeString(programVideo);
        dest.writeString(duration);
        dest.writeString(programDescription);
    }

    @Override
    public int describeContents() {
        return 0;
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
}
