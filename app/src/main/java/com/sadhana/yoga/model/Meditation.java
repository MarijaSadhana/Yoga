package com.sadhana.yoga.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Meditation implements Parcelable {

    private String meditationTitle, meditationImage, meditationVideo, duration, meditationDescription;

    private Meditation(Parcel in) {
        meditationTitle = in.readString();
        meditationImage = in.readString();
        meditationVideo = in.readString();
        duration = in.readString();
        meditationDescription = in.readString();
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
        dest.writeString(meditationTitle);
        dest.writeString(meditationImage);
        dest.writeString(meditationVideo);
        dest.writeString(duration);
        dest.writeString(meditationDescription);
    }

    public Meditation(String meditationTitle, String meditationImage, String meditationVideo, String duration, String meditationDescription) {
        this.meditationTitle = meditationTitle;
        this.meditationImage = meditationImage;
        this.meditationVideo = meditationVideo;
        this.duration = duration;
        this.meditationDescription = meditationDescription;
    }

    public String getMeditationTitle() {
        return meditationTitle;
    }

    public void setMeditationTitle(String meditationTitle) {
        this.meditationTitle = meditationTitle;
    }

    public String getMeditationImage() {
        return meditationImage;
    }

    public void setMeditationImage(String meditationImage) {
        this.meditationImage = meditationImage;
    }

    public String getMeditationVideo() {
        return meditationVideo;
    }

    public void setMeditationVideo(String meditationVideo) {
        this.meditationVideo = meditationVideo;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMeditationDescription() {
        return meditationDescription;
    }

    public void setMeditationDescription(String meditationDescription) {
        this.meditationDescription = meditationDescription;
    }

    public static Creator<Meditation> getCREATOR() {
        return CREATOR;
    }
}
