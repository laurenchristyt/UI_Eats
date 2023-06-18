package com.project.ui_eats;
import android.app.FragmentTransaction;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import android.view.Window;

import com.project.ui_eats.model.User;

public class MenuActivity extends FragmentActivity {

    static User accountLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.id_fragment_list, new MenuListFragment());
        fragmentTransaction.commit();
    }
}
