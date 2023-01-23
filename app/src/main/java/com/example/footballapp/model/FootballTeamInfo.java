package com.example.footballapp.model;

import com.google.gson.annotations.SerializedName;

public class FootballTeamInfo {

    @SerializedName("query")
    private Query query;

    @SerializedName("data")
    private Datum data;

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Datum getData() {
        return data;
    }

    public void setData(Datum data) {
        this.data = data;
    }


}

