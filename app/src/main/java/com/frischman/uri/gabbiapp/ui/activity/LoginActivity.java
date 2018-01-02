package com.frischman.uri.gabbiapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.databinding.ActivityLoginBinding;
import com.frischman.uri.gabbiapp.ui.fragment.LoginFragment;

import static com.frischman.uri.gabbiapp.utility.FragmentUtil.replaceViewWithFragment;
import static com.frischman.uri.gabbiapp.utility.SharedPreferencesUtil.checkIfSharedPreferencesContainsKey;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding mBinding;
    private static final String TAG = "LoginActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkIfUserIsLoggedIn();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        replaceViewWithFragment(getSupportFragmentManager(), R.id.login_activity_container, new LoginFragment(), false);
    }

    private void checkIfUserIsLoggedIn() {
        if (checkIfSharedPreferencesContainsKey(getApplicationContext(), "user_preferences", Context.MODE_PRIVATE, "user_info")) {
            Log.d(TAG, "onCreate: User is in shared preferences");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        } else {
            Log.d(TAG, "onCreate: User is not in shared preferences");
        }
    }

}
