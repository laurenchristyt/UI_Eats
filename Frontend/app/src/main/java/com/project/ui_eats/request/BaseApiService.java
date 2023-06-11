package com.project.ui_eats.request;

import com.project.ui_eats.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaseApiService {
    /**
     * Gets the account with the specified id.
     *
     * @param id the id of the account to retrieve
     * @return a {@link Call} object representing the API call
     */
    @GET("account/{id}")
    Call<User> getAccount(@Path("id") int id);

    /**
     * Registers a new user with the specified name, email, and password.
     *
     * @param name     the name of the user
     * @param email    the email of the user
     * @param password the password of the user
     * @return a {@link Call} object representing the API call
     */
    @POST("account/register")
    Call<User> register(@Query("name") String name,
                        @Query("email") String email,
                        @Query("full name") String full_name,
                        @Query("password") String password);

    /**
     * Attempts to log in with the specified email and password.
     * @param email    the email of the user
     * @param password the password of the user
     * @return a {@link Call} object representing the API call
     */

    @POST("account/login")
    Call<User> login(@Query("email") String email,
                     @Query("password") String password);

}