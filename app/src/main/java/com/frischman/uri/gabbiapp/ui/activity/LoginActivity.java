package com.frischman.uri.gabbiapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.ui.fragment.LoginFragment;

import static com.frischman.uri.gabbiapp.utility.SharedPreferencesUtil.checkIfSharedPreferencesContainsKey;

public class LoginActivity extends GabbiAppBaseActivity {

    private static final String TAG = "LoginActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkIfUserIsLoggedIn();
        addFragmentToActivity(new LoginFragment());
    }

    private void checkIfUserIsLoggedIn() {
        if (checkIfSharedPreferencesContainsKey(getApplicationContext(), getString(R.string.preferences_name_user_preferences), Context.MODE_PRIVATE, getString(R.string.preferences_key_user_info))) {
            Log.d(TAG, "onCreate: User is in shared preferences");
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            this.finish();
        } else {
            Log.d(TAG, "onCreate: User is not in shared preferences");
        }
    }

}
