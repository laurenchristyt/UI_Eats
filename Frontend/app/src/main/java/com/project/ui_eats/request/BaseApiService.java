package com.project.ui_eats.request;


import com.project.ui_eats.model.DeliveryDetails;
import com.project.ui_eats.model.User;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BaseApiService {
    /**
     * Gets the account with the specified id.
     *
     * @param id the id of the account to retrieve
     * @return a {@link Call} object representing the API call
     */
    @GET("users/get/{id}")
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


    /**  @FormUrlEncoded
    @POST("deliveryDetails/add")
    Call<DeliveryDetails> createDelivery (@Field("id") int id,
                                          @Field("deliveryName") String deliveryName,
                                          @Field("deliveryAddress") String deliveryAddress,
                                          @Field("deliveryPhoneNumber") int deliveryPhoneNumber,
                                          @Field("deliveryNote") String deliveryNote
    ); */

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

    @FormUrlEncoded
    @POST("deliveryDetails/{id}/add")
    Call<DeliveryDetails> registerRenter(@Field("id") int id,
                                         @Field("deliveryName") String deliveryName,
                                         @Field("deliveryAddress") String deliveryAddress,
                                         @Field("deliveryPhoneNumber") String deliveryPhoneNumber);

    @FormUrlEncoded
    @PUT("topUp/{id}/modify")
    Call<Boolean> topUp(@Field("id") int id,
                        @Field("balance") int balance);

}