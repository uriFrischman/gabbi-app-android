package com.frischman.uri.gabbiapp.ui.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.databinding.FragmentSignupBinding;
import com.frischman.uri.gabbiapp.loader.UserSignUpRequestLoader;
import com.frischman.uri.gabbiapp.model.User;
import com.frischman.uri.gabbiapp.network.response.UserSignUpResponse;
import com.frischman.uri.gabbiapp.ui.activity.MainActivity;

import static com.frischman.uri.gabbiapp.utility.SharedPreferencesUtil.putObjectInSharedPreferences;

public class SignUpFragment extends Fragment implements LoaderManager.LoaderCallbacks<UserSignUpResponse> {

    private SignUpFragment mContext = this;
    private FragmentSignupBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false);
        initializeOnClickListeners();

        return mBinding.getRoot();
    }

    private void initializeOnClickListeners() {
        mBinding.signupFragmentSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(mBinding.signupFragmentUsername.getText().toString(),
                        mBinding.signupFragmentFirstname.getText().toString(),
                        mBinding.signupFragmentLastname.getText().toString(),
                        mBinding.signupFragmentIsGabbi.isChecked(),
                        mBinding.signupFragmentEmail.getText().toString(),
                        mBinding.signupFragmentPhoneNumber.getText().toString(),
                        mBinding.signupFragmentPassword.getText().toString()
                );

                Bundle args = new Bundle();
                args.putParcelable(getString(R.string.user_bundle_key), user);

                getActivity().getSupportLoaderManager().restartLoader(0, args, mContext).forceLoad();

            }
        });
    }

    @Override
    public Loader<UserSignUpResponse> onCreateLoader(int id, Bundle args) {
        User user = args.getParcelable(getString(R.string.user_bundle_key));
        return new UserSignUpRequestLoader(getActivity().getApplicationContext(), user);
    }

    @Override
    public void onLoadFinished(Loader<UserSignUpResponse> loader, UserSignUpResponse data) {
        if (data.isSuccesfulSignUp()) {
            putObjectInSharedPreferences(getActivity(), getString(R.string.user_info_shared_preferences_key), data.getUser());
            Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

    @Override
    public void onLoaderReset(Loader<UserSignUpResponse> loader) {

    }
}
