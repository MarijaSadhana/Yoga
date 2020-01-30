//package com.example.yoga.model;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//public class News implements Parcelable {
//
//    String webTitle, webUrl, pillarName, webPublicationDate;
//
//    public News(String webTitle, String webUrl, String pillarName, String webPublicationDate) {
//        this.webTitle = webTitle;
//        this.webUrl = webUrl;
//        this.pillarName = pillarName;
//        this.webPublicationDate = webPublicationDate;
//    }
//
//    protected News(Parcel in) {
//        webTitle = in.readString();
//        webUrl = in.readString();
//        pillarName = in.readString();
//        webPublicationDate = in.readString();
//    }
//
//    public static final Creator<News> CREATOR = new Creator<News>() {
//        @Override
//        public News createFromParcel(Parcel in) {
//            return new News(in);
//        }
//
//        @Override
//        public News[] newArray(int size) {
//            return new News[size];
//        }
//    };
//
//    public String getWebTitle() {
//        return webTitle;
//    }
//
//    public void setWebTitle(String webTitle) {
//        this.webTitle = webTitle;
//    }
//
//    public String getWebUrl() {
//        return webUrl;
//    }
//
//    public void setWebUrl(String webUrl) {
//        this.webUrl = webUrl;
//    }
//
//    public String getPillarName() {
//        return pillarName;
//    }
//
//    public void setPillarName(String pillarName) {
//        this.pillarName = pillarName;
//    }
//
//    public String getWebPublicationDate() {
//        return webPublicationDate;
//    }
//
//    public void setWebPublicationDate(String publicationDate) {
//        this.webPublicationDate = publicationDate;
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(webTitle);
//        dest.writeString(webUrl);
//        dest.writeString(pillarName);
//        dest.writeString(webPublicationDate);
//    }
//}
