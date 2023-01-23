package com.example.footballapp.model;


import com.google.gson.annotations.SerializedName;


public class Country {

    @SerializedName("country_id")
    private Integer countryId;

    @SerializedName("name")
    private String name;

    @SerializedName("country_code")
    private String countryCode;

    @SerializedName("continent")
    private String continent;

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

}