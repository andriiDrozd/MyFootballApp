package com.example.footballapp.model;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Datum {

    @SerializedName("team_id")
    private Integer teamId;

    @SerializedName("name")
    private String name;

    @SerializedName("short_code")
    private String shortCode;

    @SerializedName("common_name")
    private Object commonName;

    @SerializedName("logo")
    private String logo;

    @SerializedName("country")
    private Country country;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public Object getCommonName() {
        return commonName;
    }

    public void setCommonName(Object commonName) {
        this.commonName = commonName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @BindingAdapter("profileImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions().circleCrop())
                .into(view);
    }

    @Override
    public String toString() {
        return "Datum{" +
                "teamId=" + teamId +
                ", name='" + name + '\'' +
                ", shortCode='" + shortCode + '\'' +
                ", commonName=" + commonName +
                ", logo='" + logo + '\'' +
                ", country=" + country +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Datum datum = (Datum) o;
        return teamId.equals(datum.teamId) && name.equals(datum.name) && shortCode.equals(datum.shortCode) && commonName.equals(datum.commonName) && logo.equals(datum.logo) && country.equals(datum.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, name, shortCode, commonName, logo, country);
    }

    public static DiffUtil.ItemCallback<Datum> itemCallback=new DiffUtil.ItemCallback<Datum>() {
        @Override
        public boolean areItemsTheSame(@NonNull Datum oldItem, @NonNull Datum newItem) {
            return oldItem.getTeamId().equals(newItem.teamId);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Datum oldItem, @NonNull Datum newItem) {
            return oldItem.equals(newItem);
        }
    };
}