package com.example.yoga.model;

import android.os.Parcel;

public class NewsResponse {

    public Response response;

    public NewsResponse(Parcel in, Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
