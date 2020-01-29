package com.example.yoga.model;

import java.util.ArrayList;

public class NewsResponse {

    String response;
    ArrayList<NewsResponse> results;

//    public NewsResponse(){}

    public NewsResponse(String response, ArrayList<NewsResponse> results) {
        this.response = response;
        this.results = results;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public ArrayList<NewsResponse> getResults() {
        return results;
    }

    public void setResults(ArrayList<NewsResponse> results) {
        this.results = results;
    }
}
