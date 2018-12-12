package com.example.sumansinghrajput.newsfeed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Suman Singh Rajput on 25-04-2018.
 */

public interface API {
    @GET("/{endpoint}")
    public Call<GetResponse> getApires(@Path("endpoint") String endPoint, @Query("phrase")String query);
}
