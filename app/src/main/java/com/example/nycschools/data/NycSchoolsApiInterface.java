package com.example.nycschools.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NycSchoolsApiInterface {
    @GET("/resource/s3k6-pzi2.json")
    Call<List<School>> getSchools();

    @GET("/resource/f9bf-2cp4.json")
    Call<List<SatResults>> getSatResults(@Query("dbn") String dbn);
}
