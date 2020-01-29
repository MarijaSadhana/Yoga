package com.example.yoga.model;

import java.util.ArrayList;

public class MeditationResponse {

    private int numberOfItems;
    private ArrayList<Meditation> meditations;

    public MeditationResponse(){}

    public MeditationResponse(int numberOfItems, ArrayList<Meditation> meditations) {
        this.numberOfItems = numberOfItems;
        this.meditations = meditations;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public ArrayList<Meditation> getMeditations() {
        return meditations;
    }

    public void setMeditations(ArrayList<Meditation> meditations) {
        this.meditations = meditations;
    }
}
