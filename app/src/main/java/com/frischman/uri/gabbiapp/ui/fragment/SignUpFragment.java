package com.frischman.uri.gabbiapp.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.databinding.FragmentSignupBinding;


public class SignUpFragment extends Fragment {

    private static final String TAG = "SignUpFragment";
    private FragmentSignupBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false);


        mBinding.signupFragmentSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Button clicked!");
            }
        });

        return mBinding.getRoot();
    }

    private Bundle gatherInputsToBundle() {
        Bundle args = new Bundle();
        args.putString("firstName", mBinding.signupFragmentFirstname.getText().toString());
        args.putString("lastName", mBinding.signupFragmentLastname.getText().toString());
        args.putString("username", mBinding.signupFragmentUsername.getText().toString());
        args.putString("password", mBinding.signupFragmentPassword.getText().toString());
        args.putString("email", mBinding.signupFragmentEmail.getText().toString());
        args.putString("phoneNumber", mBinding.signupFragmentPhoneNumber.getText().toString());
        args.putBoolean("isGabbi", mBinding.signupFragmentIsGabbi.isChecked());
        return args;
    }
}
