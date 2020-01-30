package com.example.yoga.model;

import java.util.ArrayList;

public class ProgramsResponse {

    private int numberOfItems;
    private ArrayList<Programs> programs;

    public ProgramsResponse(int numberOfItems, ArrayList<Programs> programs) {
        this.numberOfItems = numberOfItems;
        this.programs = programs;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public ArrayList<Programs> getPrograms() {
        return programs;
    }

    public void setPrograms(ArrayList<Programs> programs) {
        this.programs = programs;
    }
}
