package com.example.yoga.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class AsanaResponse implements Parcelable {

    private int numberOfItems;
    private ArrayList<Asanas> asanas;

    public AsanaResponse(int numberOfItems, ArrayList<Asanas> asanas) {
        this.numberOfItems = numberOfItems;
        this.asanas = asanas;
    }

    protected AsanaResponse(Parcel in) {
        numberOfItems = in.readInt();
    }

    public static final Creator<AsanaResponse> CREATOR = new Creator<AsanaResponse>() {
        @Override
        public AsanaResponse createFromParcel(Parcel in) {
            return new AsanaResponse(in);
        }

        @Override
        public AsanaResponse[] newArray(int size) {
            return new AsanaResponse[size];
        }
    };

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public ArrayList<Asanas> getAsanas() {
        return asanas;
    }

    public void setAsanas(ArrayList<Asanas> asanas) {
        this.asanas = asanas;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(numberOfItems);
    }
}
