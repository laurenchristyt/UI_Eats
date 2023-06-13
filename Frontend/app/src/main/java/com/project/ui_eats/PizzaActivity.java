package com.project.ui_eats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.project.ui_eats.request.BaseApiService;
import com.project.ui_eats.request.UtilsApi;

import com.project.ui_eats.model.Pizza;
import com.project.ui_eats.model.Topping;

public class PizzaActivity extends AppCompatActivity {
    /**
     * A {@link BaseApiService} instance for making API requests.
     */
    BaseApiService mApiService;
    /**
     * The {@link Context} of the activity.
     */
    Context mContext;
    Button buttonCreateOrder;
    RadioButton mushroom, pepperoni, onions;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);

        radioGroup = findViewById(R.id.radioGroup);

        mushroom = findViewById(R.id.rbMushroom);
        pepperoni = findViewById(R.id.rbPepperoni);
        onions = findViewById(R.id.rbOnions);

        mApiService = UtilsApi.getApiService();
        mContext = this;

        buttonCreateOrder = findViewById(R.id.btnCreateOrder);
        buttonCreateOrder.setOnClickListener(a -> {
            createOrder();
        });
    }

    protected void createOrder() {
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            String toppingText = selectedRadioButton.getText().toString().toUpperCase();
            Topping topping = Topping.valueOf(toppingText);

            mApiService.createOrder(topping).enqueue(new Callback<Pizza>() {
                @Override
                public void onResponse(Call<Pizza> call, Response<Pizza> response) {
                    System.out.println("pizzaaaaaa Successful" + response);
                    Toast.makeText(mContext, "Pizza Created", Toast.LENGTH_SHORT).show();
                    Intent move = new Intent(PizzaActivity.this, MainActivity.class);
                    startActivity(move);
                }

                @Override
                public void onFailure(Call<Pizza> call, Throwable t) {
                    Toast.makeText(mContext, "Pizza Creation Failed", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(mContext, "Please select a topping", Toast.LENGTH_SHORT).show();
        }
    }
}
