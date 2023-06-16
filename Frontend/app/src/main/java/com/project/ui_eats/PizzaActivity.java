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

import com.project.ui_eats.model.Crust;
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
    RadioButton thin, thick, bread, stuffed;
    private RadioGroup radioGroup;
    private RadioGroup secondradioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);

        radioGroup = findViewById(R.id.toppingRadioGroup);
        secondradioGroup = findViewById(R.id.crustRadioGroup);

        mushroom = findViewById(R.id.rbMushroom);
        pepperoni = findViewById(R.id.rbPepperoni);
        onions = findViewById(R.id.rbOnions);

        thin = findViewById(R.id.rbThin);
        thick = findViewById(R.id.rbThick);
        bread = findViewById(R.id.rbBread);
        stuffed = findViewById(R.id.rbStuffed);

        mApiService = UtilsApi.getApiService();
        mContext = this;

        buttonCreateOrder = findViewById(R.id.btnCreateOrder);
        buttonCreateOrder.setOnClickListener(a -> {
            createOrder();
        });
    }

    protected void createOrder() {
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        int crustselectedRadioButtonId = secondradioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1 && crustselectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            RadioButton crustselectedRadioButton = findViewById(crustselectedRadioButtonId);
            String toppingText = selectedRadioButton.getText().toString().toUpperCase();
            String crusttoppingText = crustselectedRadioButton.getText().toString().toUpperCase();
            Topping topping = Topping.valueOf(toppingText);
            Crust crust = Crust.valueOf(crusttoppingText);

            System.out.println(MainActivity.accountLogin.account_id);

            mApiService.createOrder(MainActivity.accountLogin.account_id, topping, crust).enqueue(new Callback<Pizza>() {
                @Override
                public void onResponse(Call<Pizza> call, Response<Pizza> response) {
                    System.out.println("pizza Successful " + response);
                    Toast.makeText(mContext, "Pizza Created", Toast.LENGTH_SHORT).show();
                    Intent move = new Intent(PizzaActivity.this, CreateDelivery.class);
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
