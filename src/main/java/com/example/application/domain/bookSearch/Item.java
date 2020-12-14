
package com.example.application.domain.bookSearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {


    @SerializedName("volumeInfo")
    @Expose
    private VolumeInfo volumeInfo;


    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }


    @Override
    public String toString() {
        return
                "volumeInfo=" + volumeInfo
                ;
    }
}



