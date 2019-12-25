
package com.app.engineeringaitest.response;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("image")
    private String mImage;
    @SerializedName("items")
    private ArrayList<String> mItems;
    @SerializedName("name")
    private String mName;

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public ArrayList<String> getItems() {
        return mItems;
    }

    public void setItems(ArrayList<String> items) {
        mItems = items;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
