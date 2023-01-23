package com.example.footballapp.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FootballInfo {

    @SerializedName("query")
    private Query query;

    @SerializedName("data")
    private List<Datum> data = new ArrayList<>();

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}