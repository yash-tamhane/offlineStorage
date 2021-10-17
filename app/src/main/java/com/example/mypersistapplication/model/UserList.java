package com.example.mypersistapplication.model;

import com.google.gson.annotations.SerializedName;

public class UserList {

    @SerializedName("results")
    private Results[] results;


    public Results[] getResults()
    {
        return results;
    }

    public void setResults (Results[] results)
    {
        this.results = results;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [results = "+results +"]";
    }
}
