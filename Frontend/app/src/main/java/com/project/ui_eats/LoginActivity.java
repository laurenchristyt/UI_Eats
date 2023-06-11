package com.project.ui_eats;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.project.ui_eats.model.User;
import com.project.ui_eats.request.BaseApiService;
import com.project.ui_eats.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    BaseApiService mApiService;
    Context mContext;
    EditText name, password;
    static User accountLogin;

    private Button btnLogin;
    private Button btnRegisterNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.buttonLogin);
        btnRegisterNow = findViewById(R.id.btnRegisterNow);

        mApiService = UtilsApi.getApiService();
        mContext = this;

        name = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User login = requestLogin();
            }
        });

        btnRegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    protected User requestLogin(){
        mApiService.login(name.getText().toString(), password.getText().toString()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Dashboard.accountLogin = response.body();
                    Intent move = new Intent(LoginActivity.this, Dashboard.class);
                    startActivity(move);
                    Toast.makeText(mContext, "Login Successful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(mContext, "Login unsuccessful", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}