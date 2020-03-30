package com.sadhana.yoga.model;

import java.util.ArrayList;

public class PranayamaResponse {

    private int numberOfItems;
    private ArrayList<Pranayama> pranayamas;

    public PranayamaResponse(){}

    public PranayamaResponse(int numberOfItems, ArrayList<Pranayama> pranayamas) {
        this.numberOfItems = numberOfItems;
        this.pranayamas = pranayamas;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public ArrayList<Pranayama> getPranayamas() {
        return pranayamas;
    }

    public void setPranayamas(ArrayList<Pranayama> pranayamas) {
        this.pranayamas = pranayamas;
    }
}
