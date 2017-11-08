package com.frischman.uri.gabbiapp.ui.fragment;

import android.content.Intent;
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
import com.frischman.uri.gabbiapp.loader.UserLoginRequestLoader;
import com.frischman.uri.gabbiapp.network.response.UserLoginResponse;
import com.frischman.uri.gabbiapp.ui.activity.MainActivity;

import static android.content.ContentValues.TAG;
import static com.frischman.uri.gabbiapp.utility.FragmentUtil.replaceViewWithFragment;

public class LoginFragment extends Fragment implements LoaderManager.LoaderCallbacks<UserLoginResponse> {

    private FragmentLoginBinding mBinding;
    private LoginFragment mContext = this;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        initializeOnClickListeners();

        return mBinding.getRoot();
    }

    private void initializeOnClickListeners() {
        mBinding.loginFragmentLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("username", mBinding.loginUsernameField.getText().toString());
                args.putString("password", mBinding.loginPasswordField.getText().toString());

                getActivity().getSupportLoaderManager().restartLoader(1, args, mContext).forceLoad();
            }
        });

        mBinding.loginFragmentSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceViewWithFragment(getActivity().getSupportFragmentManager(), R.id.login_activity_container, new SignUpFragment(), true);
            }
        });
    }

    @Override
    public Loader<UserLoginResponse> onCreateLoader(int id, Bundle args) {
        String username = args.getString("username");
        String password = args.getString("password");
        return new UserLoginRequestLoader(getActivity().getApplicationContext(), username, password);
    }

    @Override
    public void onLoadFinished(Loader<UserLoginResponse> loader, UserLoginResponse data) {
        Log.d(TAG, "onLoadFinished: " + data.toString());
        if (data.isSuccessfulLogin()) {
            Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

    @Override
    public void onLoaderReset(Loader<UserLoginResponse> loader) {

    }
}
