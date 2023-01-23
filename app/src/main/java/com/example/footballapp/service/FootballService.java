package com.example.footballapp.service;


import com.example.footballapp.model.FootballInfo;
import com.example.footballapp.model.FootballTeamInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FootballService {

    @GET("api/v1/soccer/teams")
    Call<FootballInfo> getFootballInfo(@Query("country_id") int countryId, @Query("apikey") String apiKey);

    @GET("api/v1/soccer/teams/{team_id}")
    Call<FootballTeamInfo> getTeamInfo(@Path("team_id") int teamId, @Query("apikey") String apiKey);
}
