package com.example.footballapp.model;

import com.google.gson.annotations.SerializedName;

public class Query {

    @SerializedName("apikey")
    private String apikey;

    @SerializedName("country_id")
    private String countryId;

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

}

