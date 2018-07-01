package com.frischman.uri.gabbiapp.ui.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.frischman.uri.gabbiapp.ui.fragment.DafYomiFragment;

public class DafYomiActivity extends GabbiAppBaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DafYomiFragment dafYomiFragment = new DafYomiFragment();
//        FragmentUtil.replaceViewWithFragment(getSupportFragmentManager(), R.id.container, dafYomiFragment, false);
//        mBinding.slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        addFragmentToActivity(new DafYomiFragment());
    }
}
