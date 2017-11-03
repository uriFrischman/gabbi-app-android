package com.frischman.uri.gabbiapp.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.databinding.FragmentLoginBinding;
import com.frischman.uri.gabbiapp.loader.UserSignUpRequestLoader;
import com.frischman.uri.gabbiapp.model.User;
import com.frischman.uri.gabbiapp.network.response.UserSignUpResonse;

public class LoginFragment extends Fragment implements LoaderManager.LoaderCallbacks<UserSignUpResonse> {

    private static final String TAG = "LoginFragment";
    private FragmentLoginBinding mBinding;
    private LoginFragment mContext = this;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        mBinding.loginFragmentLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User("uriFrischman", "Uri", "Frischman", false, "email@email.com", "phone", "password");
                Bundle args = new Bundle();
                args.putParcelable("user", user);

                getActivity().getSupportLoaderManager().restartLoader(0, args, mContext).forceLoad();
            }
        });

        return mBinding.getRoot();
    }

    @Override
    public Loader<UserSignUpResonse> onCreateLoader(int id, Bundle args) {
        User user = args.getParcelable("user");
        Log.d(TAG, "onCreateLoader: " + user);
        return new UserSignUpRequestLoader(getActivity().getApplicationContext(), user);
    }

    @Override
    public void onLoadFinished(Loader<UserSignUpResonse> loader, UserSignUpResonse data) {
        Log.d(TAG, "onLoadFinished: " + data.isSuccesfulSignUp());
    }

    @Override
    public void onLoaderReset(Loader<UserSignUpResonse> loader) {

    }
}
