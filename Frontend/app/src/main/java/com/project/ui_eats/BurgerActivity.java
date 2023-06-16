package com.project.ui_eats;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.project.ui_eats.model.Burger;
import com.project.ui_eats.model.Orders;
import com.project.ui_eats.request.BaseApiService;
import com.project.ui_eats.request.UtilsApi;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BurgerActivity extends AppCompatActivity {

    BaseApiService mApiService;
    /**
     * The {@link Context} of the activity.
     */
    Context mContext;
    CheckBox cheeseBurger, beefBurger, chickenBurger;
    ArrayList<Orders> burger_type = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burger);

        Button cartButton = findViewById(R.id.addToCartButton);
        cartButton.setOnClickListener(a -> {
                    addBurgertoCart();
                }
        );

        mApiService = UtilsApi.getApiService();
        mContext = this;
    }

    protected Orders addBurgertoCart() {
        EditText burger_note = findViewById(R.id.noteEditText);
        checkBoxBurger();
        mApiService.createBurger(burger_type, burger_note.getText().toString())
                .enqueue(new Callback<Burger>() {
            @Override
            public void onResponse(Call<Burger> call, Response<Burger> response) {
                System.out.println(burger_type);
                System.out.println("success" + response);
                Toast.makeText(mContext, "Room Created", Toast.LENGTH_SHORT).show();
                Intent move = new Intent(BurgerActivity.this, MainActivity.class);
                startActivity(move);
            }

            @Override
            public void onFailure(Call<Burger> call, Throwable t) {
                Toast.makeText(mContext, "Room Creation Failed", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }

    void checkBoxBurger() {
        cheeseBurger = findViewById(R.id.cheeseBurgerCheckBox);
        beefBurger = findViewById(R.id.beefBurgerCheckBox);
        chickenBurger = findViewById(R.id.chickenBurgerCheckBox);

        if (cheeseBurger.isChecked()) {
            burger_type.add(Orders.cheeseBurger);
        }
        if (beefBurger.isChecked()) {
            burger_type.add(Orders.beefBurger);
        }
        if (chickenBurger.isChecked()) {
            burger_type.add(Orders.chickenBurger);
        }
    }
}