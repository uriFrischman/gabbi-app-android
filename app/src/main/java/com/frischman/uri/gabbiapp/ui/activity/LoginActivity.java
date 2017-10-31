package com.frischman.uri.gabbiapp.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.databinding.ActivityLoginBinding;
import com.frischman.uri.gabbiapp.ui.fragment.LoginFragment;

import static com.frischman.uri.gabbiapp.utility.FragmentUtil.replaceViewWithFragment;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding mBinding;
    private static final String TAG = "LoginActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        replaceViewWithFragment(getSupportFragmentManager(), R.id.login_activity_container, new LoginFragment(), false);
    }

}
