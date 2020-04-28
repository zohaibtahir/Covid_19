package com.zeetech.covid19;

import com.zeetech.covid19.Models.AllCountriesData;
import com.zeetech.covid19.Models.WholeWorldData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NovelCovid19Api {

    @GET("all")
    Call<WholeWorldData> getAllData();

    @GET("countries")
    Call<List<AllCountriesData>> getCountriesData();
}
