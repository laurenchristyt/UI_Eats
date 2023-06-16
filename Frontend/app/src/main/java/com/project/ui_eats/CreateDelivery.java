package com.project.ui_eats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.ui_eats.model.DeliveryDetails;
import com.project.ui_eats.request.BaseApiService;
import com.project.ui_eats.request.UtilsApi;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateDelivery extends AppCompatActivity {
    /**
     * A {@link BaseApiService} instance for making API requests.
     */
    BaseApiService mApiService;
    /**
     * The {@link Context} of the activity.
     */
    Context mContext;
    EditText deliveryName, deliveryPhoneNum, deliveryAddress, deliveryNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_delivery);

        mApiService = UtilsApi.getApiService();
        mContext = this;

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        Button createDelivery = findViewById(R.id.button_createDelivery);
        Button cancelCreateRoom = findViewById(R.id.button_cancelCreateDelivery);

        createDelivery.setOnClickListener(a -> {
                    createDeliveryRequest();
                }
        );
        cancelCreateRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    protected DeliveryDetails createDeliveryRequest() {
        deliveryName = findViewById(R.id.delivery_name_et);
        deliveryAddress = findViewById(R.id.delivery_address_et);
        deliveryNote = findViewById(R.id.delivery_note_et);
        deliveryPhoneNum = findViewById(R.id.delivery_phonenumber_et);
        int deliveryPhoneNumber = Integer.parseInt(deliveryPhoneNum.getText().toString());


        System.out.println(MainActivity.accountLogin.account_id);
        System.out.println(deliveryName.getText().toString());
        System.out.println(deliveryAddress.getText().toString());

        mApiService.createDelivery(
                MainActivity.accountLogin.account_id,
                deliveryName.getText().toString(),
                deliveryAddress.getText().toString(),
                deliveryPhoneNumber,
                deliveryNote.getText().toString()).enqueue(new Callback<DeliveryDetails>() {

            @Override
            public void onResponse(Call<DeliveryDetails> call, Response<DeliveryDetails> response) {
                System.out.println("delivery Successful " + response);
                Toast.makeText(mContext, "Delivery Details Added", Toast.LENGTH_SHORT).show();
                Intent move = new Intent(CreateDelivery.this, MainActivity.class);
                startActivity(move);
            }

            @Override
            public void onFailure(Call<DeliveryDetails> call, Throwable t) {
                Toast.makeText(mContext, "Delivery Details cant be Added", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}