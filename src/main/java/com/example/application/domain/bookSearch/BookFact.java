
package com.example.application.domain.bookSearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookFact {


    @SerializedName("items")
    @Expose
    private List<Item> items = null;


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    @Override
    public String toString() {
        return
                "items=" + items
                ;
    }
}
