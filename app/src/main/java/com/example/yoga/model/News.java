package com.example.yoga.model;

import android.os.Parcel;
import android.os.Parcelable;

public class News implements Parcelable {

    String title, url, publishedAt;

    public News(String title, String url, String publishedAt) {
        this.title = title;
        this.url = url;
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public static Creator<News> getCREATOR() {
        return CREATOR;
    }

    protected News(Parcel in) {
        title = in.readString();
        url = in.readString();
        publishedAt = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(url);
        dest.writeString(publishedAt);
    }
}
