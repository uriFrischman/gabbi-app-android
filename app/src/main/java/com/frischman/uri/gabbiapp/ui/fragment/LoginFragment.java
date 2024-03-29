package com.frischman.uri.gabbiapp.ui.fragment;

import android.content.Context;
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
import android.widget.Toast;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.databinding.FragmentLoginBinding;
import com.frischman.uri.gabbiapp.loader.UserLoginRequestLoader;
import com.frischman.uri.gabbiapp.network.response.UserLoginResponse;
import com.frischman.uri.gabbiapp.ui.activity.MainActivity;

import static android.content.ContentValues.TAG;
import static com.frischman.uri.gabbiapp.loader.LoaderConstants.USER_LOGIN_REQUEST_LOADER_ID;
import static com.frischman.uri.gabbiapp.utility.FragmentUtil.replaceViewWithFragment;
import static com.frischman.uri.gabbiapp.utility.SharedPreferencesUtil.putObjectInSharedPreferences;

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
                args.putString(getString(R.string.email_bundle_key), mBinding.loginEmailField.getText().toString());
                args.putString(getString(R.string.password_bundle_key), mBinding.loginPasswordField.getText().toString());

                getActivity().getSupportLoaderManager().restartLoader(USER_LOGIN_REQUEST_LOADER_ID, args, mContext).forceLoad();
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
        return new UserLoginRequestLoader(getActivity().getApplicationContext(), args.getString(getString(R.string.email_bundle_key)).toLowerCase(), args.getString(getString(R.string.password_bundle_key)));
    }

    @Override
    public void onLoadFinished(Loader<UserLoginResponse> loader, UserLoginResponse data) {
        Log.d(TAG, "onLoadFinished: " + data.toString());
        Toast.makeText(getActivity().getApplicationContext(), data.getMessage(), Toast.LENGTH_SHORT).show();
        if (data.isSuccessfulLogin()) {
            putObjectInSharedPreferences(getActivity().getApplicationContext(), getString(R.string.preferences_name_user_preferences), Context.MODE_PRIVATE, getString(R.string.preferences_key_user_info), data.getUser());
            Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

    @Override
    public void onLoaderReset(Loader<UserLoginResponse> loader) {

    }
}
