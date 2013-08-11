package br.com.android.menus.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public abstract class BaseModel implements Serializable{
    @SerializedName("id")
    private int mId;

    public int getId(){
        return mId;
    }

    public void setId(int id){
        this.mId = id;
    }
}
