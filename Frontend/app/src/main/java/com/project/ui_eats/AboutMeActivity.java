package com.project.ui_eats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.ui_eats.model.DeliveryDetails;

import com.project.ui_eats.model.User;
import com.project.ui_eats.model.DeliveryDetails;
import com.project.ui_eats.request.BaseApiService;
import com.project.ui_eats.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The AboutMeActivity class is an Android activity that represents the account's of specific user.
 *
 * @author Lauren Christy Tanudjaja
 * @version 1.0
 */
public class AboutMeActivity extends AppCompatActivity {
    /**
     * The {@link TextView} that displays the user's name, user's email, user's balance,
     * the amount the user wants to top up their account with, option for user to log out, and name,
     * address, and phone number of the registered renter.
     */
    TextView username, email, balance;
    TextView renterName, renterAddress, renterPhone;
    /**
     * The {@link EditText} where the user can enter the name, address, and phone number of a renter to register.
     */
    EditText editName, editAddress, editPhone, amount;
    /**
     * A {@link BaseApiService} instance for making API requests.
     */
    BaseApiService mApiService;
    /**
     * The {@link Context} of the activity.
     */
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        try
        {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){}

        mApiService = UtilsApi.getApiService();
        mContext = this;

        User account = MenuActivity.accountLogin;

        username = findViewById(R.id.nameAboutMe);
        email = findViewById(R.id.emailAboutMe);
        balance = findViewById(R.id.balanceAboutMe);
        amount = findViewById(R.id.amount);

        Button registerRenterButton = findViewById(R.id.RegisterRenterButton);
        Button cancelButton = findViewById(R.id.CancelButton);
        Button topUpButton = findViewById(R.id.TopUpButton);
        Button registerRenter = findViewById(R.id.RegisterButton);
        Button btnNext = findViewById(R.id.nextButton);

        CardView renterCard = findViewById(R.id.cardViewRenter);
        CardView registerCard = findViewById(R.id.cardViewRegister);

        renterName = findViewById(R.id.RenterNameView);
        renterAddress = findViewById(R.id.RenterAddressView);
        renterPhone = findViewById(R.id.RenterPhoneNumberView);

        editName = findViewById(R.id.RegisterName);
        editAddress = findViewById(R.id.RegisterAddress);
        editPhone = findViewById(R.id.RegisterPhone);

        username.setText(MenuActivity.accountLogin.username);
        email.setText(MenuActivity.accountLogin.email);
        String balanceText = "Rp. " + MenuActivity.accountLogin.balance;
        balance.setText(balanceText);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(AboutMeActivity.this, MenuActivity.class);
                startActivity(move);

            }
        });

        topUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestTopUp(MenuActivity.accountLogin.account_id,Double.parseDouble(amount.getText().toString()));
            }
        });

        if(account.details == null) {
            registerRenterButton.setVisibility(View.VISIBLE);
            renterCard.setVisibility(View.GONE);
            registerCard.setVisibility(View.GONE);

            registerRenterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    registerRenterButton.setVisibility(View.GONE);
                    renterCard.setVisibility(View.GONE);
                    registerCard.setVisibility(View.VISIBLE);

                    registerRenter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            LoginActivity.details = requestRegister();
                            Intent move = new Intent(AboutMeActivity.this, LoginActivity.class);
                            startActivity(move);

                        }
                    });
                    cancelButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            registerRenterButton.setVisibility(View.VISIBLE);
                            renterCard.setVisibility(View.GONE);
                            registerCard.setVisibility(View.GONE);

                            renterName.setText("");
                            renterAddress.setText("");
                            renterPhone.setText("");
                        }
                    });
                }
            });
        } else {
            registerRenterButton.setVisibility(View.GONE);
            renterCard.setVisibility(View.VISIBLE);
            registerCard.setVisibility(View.GONE);

            renterName.setText(MenuActivity.accountLogin.details.receipient);
            renterAddress.setText(MenuActivity.accountLogin.details.address);
            renterPhone.setText(MenuActivity.accountLogin.details.phoneNumber);
        }
    }

    /**
     * This function is used to top up the user's balance
     *
     * @param account_id the id
     * @param balance the user's balance
     * @return Renter
     */
    protected Boolean requestTopUp(int account_id, double balance) {
        int amountValue = (int) Double.parseDouble(amount.getText().toString());

        mApiService.topUp(MenuActivity.accountLogin.account_id, amountValue).enqueue(new Callback<Boolean>() {
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    MenuActivity.accountLogin.balance = MenuActivity.accountLogin.balance + amountValue;
                    System.out.println(response);
                    Toast.makeText(mContext, "Top Up Failed!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AboutMeActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(mContext, "Top Up Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AboutMeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        return false;
    }

    /**
     * This function is used to to request a new renter
     */
    public DeliveryDetails requestRegister() {
        mApiService.registerRenter(MenuActivity.accountLogin.account_id, editName.getText().toString(), editAddress.getText().toString(), editPhone.getText().toString()).enqueue(new Callback<DeliveryDetails>(){
            @Override
            public void onResponse(Call<DeliveryDetails> call, Response<DeliveryDetails> response) {
                if(response.isSuccessful()) {
                    DeliveryDetails renter = response.body();
                    MenuActivity.accountLogin.details = renter;
                    Intent move = new Intent(AboutMeActivity.this, AboutMeActivity.class);
                    startActivity(move);
                    Toast.makeText(mContext, "Successful!", Toast.LENGTH_LONG).show();
                    renterName.setText(MenuActivity.accountLogin.details.receipient);
                    renterAddress.setText(MenuActivity.accountLogin.details.address);
                    renterPhone.setText(MenuActivity.accountLogin.details.phoneNumber);
                }
            }

            @Override
            public void onFailure(Call<DeliveryDetails> call, Throwable t) {
                Toast.makeText(mContext, "Failed!", Toast.LENGTH_LONG).show();
            }
        });
        return null;
    }


}