package com.project.ui_eats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.project.ui_eats.model.User;

public class MainActivity extends AppCompatActivity {
    static User accountLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}