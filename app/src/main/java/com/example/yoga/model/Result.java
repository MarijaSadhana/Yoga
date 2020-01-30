package com.example.yoga.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Result implements Parcelable {

    public String id, type, sectionId, sectionName, webPublicationDate, webTitle, webUrl, apiUrl, pillarId, pillarName;
    public Boolean isHosted;

    public Result(String id, String type, String sectionId, String sectionName, String webPublicationDate, String webTitle, String webUrl, String apiUrl, String pillarId, String pillarName, Boolean isHosted) {
        this.id = id;
        this.type = type;
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.webPublicationDate = webPublicationDate;
        this.webTitle = webTitle;
        this.webUrl = webUrl;
        this.apiUrl = apiUrl;
        this.pillarId = pillarId;
        this.pillarName = pillarName;
        this.isHosted = isHosted;
    }

    protected Result(Parcel in) {
        id = in.readString();
        type = in.readString();
        sectionId = in.readString();
        sectionName = in.readString();
        webPublicationDate = in.readString();
        webTitle = in.readString();
        webUrl = in.readString();
        apiUrl = in.readString();
        pillarId = in.readString();
        pillarName = in.readString();
        byte tmpIsHosted = in.readByte();
        isHosted = tmpIsHosted == 0 ? null : tmpIsHosted == 1;
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public void setWebPublicationDate(String webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getPillarId() {
        return pillarId;
    }

    public void setPillarId(String pillarId) {
        this.pillarId = pillarId;
    }

    public String getPillarName() {
        return pillarName;
    }

    public void setPillarName(String pillarName) {
        this.pillarName = pillarName;
    }

    public Boolean getHosted() {
        return isHosted;
    }

    public void setHosted(Boolean hosted) {
        isHosted = hosted;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(type);
        dest.writeString(sectionId);
        dest.writeString(sectionName);
        dest.writeString(webPublicationDate);
        dest.writeString(webTitle);
        dest.writeString(webUrl);
        dest.writeString(apiUrl);
        dest.writeString(pillarId);
        dest.writeString(pillarName);
        dest.writeByte((byte) (isHosted == null ? 0 : isHosted ? 1 : 2));
    }
}
