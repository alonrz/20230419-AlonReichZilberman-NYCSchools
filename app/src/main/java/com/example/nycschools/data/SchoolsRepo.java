package com.example.nycschools.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SchoolsRepo {
    private static final String URL = "https://data.cityofnewyork.us/";

    private final NycSchoolsApiInterface apiInterface =
            new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(NycSchoolsApiInterface.class);

    /*
    This method will take care of moving the network request to a different thread, and is main-safe
     */
    public void fetchSchools(SchoolsFetchResult schoolsFetchResult) {
        apiInterface.getSchools().enqueue(new Callback<List<School>>() {
            @Override
            public void onResponse(Call<List<School>> call, Response<List<School>> response) {
                schoolsFetchResult.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<School>> call, Throwable t) {
                schoolsFetchResult.onError(t);
            }
        });
    }

    public interface SchoolsFetchResult {
        void onResponse(List<School> schools);

        void onError(Throwable throwable);
    }
}