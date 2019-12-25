package com.app.engineeringaitest.network;

import com.app.engineeringaitest.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApiMethods {
    @GET("api/users")
    Call<UserResponse> getUserData(@Query("offset") int offset, @Query("limit") int limit);
}
