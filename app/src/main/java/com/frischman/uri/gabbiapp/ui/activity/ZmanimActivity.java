package com.frischman.uri.gabbiapp.ui.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.frischman.uri.gabbiapp.ui.fragment.ZmanimFragment;

public class ZmanimActivity extends GabbiAppBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragmentToActivity(new ZmanimFragment());
    }
}
