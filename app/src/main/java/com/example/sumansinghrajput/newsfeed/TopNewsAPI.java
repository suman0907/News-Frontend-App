package com.example.sumansinghrajput.newsfeed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Suman Singh Rajput on 27-04-2018.
 */

public interface TopNewsAPI {
    @GET("/{endpoint}")
    public Call<CurrentGetResponse> getApires(@Path("endpoint") String endPoint, @Query("country")String query);
}
