package com.example.yoga.model;

import java.util.ArrayList;

public class AsanaResponse {

    private int numberOfItems;
    private ArrayList<Asana> asanas;

    public AsanaResponse(int numberOfItems, ArrayList<Asana> asanas) {
        this.numberOfItems = numberOfItems;
        this.asanas = asanas;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public ArrayList<Asana> getAsanas() {
        return asanas;
    }

    public void setAsanas(ArrayList<Asana> asanas) {
        this.asanas = asanas;
    }

}
