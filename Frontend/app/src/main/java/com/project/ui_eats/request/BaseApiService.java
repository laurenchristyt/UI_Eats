package com.project.ui_eats.request;

import com.project.ui_eats.model.Topping;
import com.project.ui_eats.model.User;
import com.project.ui_eats.model.Pizza;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
     * @param username     the name of the user
     * @param email    the email of the user
     * @param password the password of the user
     * @return a {@link Call} object representing the API call
     */

    @FormUrlEncoded
    @POST("users/register")
    Call<User> register(@Field("username") String username,
                        @Field("password") String password,
                        @Field("email") String email,
                        @Field("full_name") String full_name);

    @FormUrlEncoded
    @POST("pizza/customization")
    Call<Pizza> createOrder(@Field("Topping") Topping topping
                        );
    /**
     * Attempts to log in with the specified email and password.
     * @param username    the username of the user
     * @param password the password of the user
     * @return a {@link Call} object representing the API call
     */
    @FormUrlEncoded
    @POST("users/login")
    Call<User> login(@Field("username") String username,
                     @Field("password") String password);

}