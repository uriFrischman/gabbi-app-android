package com.frischman.uri.gabbiapp.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.ui.fragment.GetTextFragment;

public class GetTextActivity extends GabbiAppBaseActivity {

    public static final String EXTRA_GET_TEXT_READING = "EXTRA_GET_TEXT_READING";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        FragmentUtil.replaceViewWithFragment(getSupportFragmentManager(), R.id.container, getTextFragment, false);
        Intent intent = getIntent();
        if (intent.getStringExtra(EXTRA_GET_TEXT_READING) == null) {
            addFragmentToActivity(new GetTextFragment());
        } else {
            GetTextFragment getTextFragment = new GetTextFragment();
            Bundle args = new Bundle();
            args.putString(getString(R.string.arg_key_reading), intent.getStringExtra(EXTRA_GET_TEXT_READING));
            getTextFragment.setArguments(args);
            addFragmentToActivity(getTextFragment);
        }

    }
}
