package com.frischman.uri.gabbiapp.ui.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.frischman.uri.gabbiapp.ui.fragment.EventListFragment;

public class EventListActivity extends GabbiAppBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragmentToActivity(new EventListFragment(this));
    }
}
