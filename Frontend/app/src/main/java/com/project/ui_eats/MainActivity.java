package com.project.ui_eats;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.project.ui_eats.model.User;

public class MainActivity extends AppCompatActivity {
    static User accountLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textViewPizza = findViewById(R.id.textViewPizza);
        TextView textViewBurger = findViewById(R.id.textViewBurger);
        TextView textViewChef = findViewById(R.id.textViewChef);
        TextView textViewSpaghetti = findViewById(R.id.textViewSpaghetti);

        textViewPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PizzaActivity.class);
                startActivity(intent);
            }
        });

        textViewBurger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BurgerActivity.class);
                startActivity(intent);
            }
        });

        textViewChef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VirtualChefActivity.class);
                startActivity(intent);
            }
        });

        textViewSpaghetti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SpaghettiActivity.class);
                startActivity(intent);
            }
        });
    }
}
