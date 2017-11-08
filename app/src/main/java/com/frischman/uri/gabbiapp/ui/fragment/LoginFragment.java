package com.frischman.uri.gabbiapp.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.databinding.FragmentLoginBinding;

import static com.frischman.uri.gabbiapp.utility.FragmentUtil.replaceViewWithFragment;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        mBinding.loginFragmentSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceViewWithFragment(getActivity().getSupportFragmentManager(), R.id.login_activity_container, new SignUpFragment(), true);
            }
        });

        return mBinding.getRoot();
    }
}
