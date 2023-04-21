package com.example.nycschools.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SatRepo {
    private static final String URL = "https://data.cityofnewyork.us/";

    private final NycSchoolsApiInterface apiInterface =
            new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(NycSchoolsApiInterface.class);

    public void fetchSatResults(SatFetchResult satFetchResult, String dbn) {
        apiInterface.getSatResults(dbn).enqueue(new Callback<List<SatResults>>() {
            @Override
            public void onResponse(Call<List<SatResults>> call, Response<List<SatResults>> response) {
                if (response.body() != null && !response.body().isEmpty()) {
                    satFetchResult.onResponse(response.body().get(0));
                }
            }

            @Override
            public void onFailure(Call<List<SatResults>> call, Throwable t) {
                satFetchResult.onError(t);
            }
        });
    }

    public interface SatFetchResult {
        void onResponse(SatResults satResults);

        void onError(Throwable throwable);
    }
}
