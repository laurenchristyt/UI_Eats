package com.project.ui_eats;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.appcompat.app.AppCompatActivity;

import com.project.ui_eats.model.User;
import com.project.ui_eats.request.BaseApiService;
import com.project.ui_eats.request.UtilsApi;

public class RegisterActivity extends AppCompatActivity {
    BaseApiService mApiService;
    Context mContext;

    private EditText name, password, email, fullname;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        try
        {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){}

        name = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        email = findViewById(R.id.etEmail);
        fullname = findViewById(R.id.etFullName);
        btnRegister = findViewById(R.id.btnRegister);

        mApiService = UtilsApi.getApiService();
        mContext = this;

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User account = requestRegister();
            }
        });
    }

    protected User requestRegister(){
        mApiService.register(name.getText().toString(), password.getText().toString(), email.getText().toString(), fullname.getText().toString()).enqueue(new Callback<User>(){
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println("Register Successful" + response);
                if(response.isSuccessful()){
                    Intent move = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(move);
                    Toast.makeText(mContext, "account registered", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(mContext, "account already registered", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}
