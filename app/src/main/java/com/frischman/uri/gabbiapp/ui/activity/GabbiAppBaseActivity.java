package com.frischman.uri.gabbiapp.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.databinding.ActivityBaseBinding;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import static com.frischman.uri.gabbiapp.utility.SharedPreferencesUtil.removeItemFromSharedPreferences;
import static com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState;

public abstract class GabbiAppBaseActivity extends AppCompatActivity {

    ActivityBaseBinding mBinding;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);

        initMenuButtons();
        initPanelSlideUpMenu();
        mBinding.slidingLayout.setPanelState(PanelState.COLLAPSED);
        mBinding.fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.slidingLayout.setPanelState(PanelState.EXPANDED);
            }
        });
    }

    public void addFragmentToActivity(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (mBinding.slidingLayout.getPanelState() == PanelState.EXPANDED) {
            mBinding.slidingLayout.setPanelState(PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }

    private void initPanelSlideUpMenu() {

        mBinding.slidingLayout.setPanelHeight(0);
        mBinding.slidingLayout.setPanelState(PanelState.COLLAPSED);
        mBinding.slidingLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelStateChanged(View panel, PanelState previousState, PanelState newState) {
                if (newState == PanelState.COLLAPSED) {
                    mBinding.slidingLayout.setPanelHeight(0);
                }
            }
        });
    }

    private void initMenuButtons() {

        mBinding.fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.slidingLayout.setPanelState(PanelState.EXPANDED);
            }
        });

        mBinding.viewMenu.eventsFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EventListActivity.class);
                startActivity(intent);
            }
        });

        mBinding.viewMenu.HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, HomeActivity.class);
                startActivity(intent);
            }
        });

        mBinding.viewMenu.ZmanimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ZmanimActivity.class);
                startActivity(intent);
            }

        });

        mBinding.viewMenu.GetTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GetTextActivity.class);
                startActivity(intent);
            }
        });

        mBinding.viewMenu.DafYomiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DafYomiActivity.class);
                startActivity(intent);
            }
        });

        mBinding.viewMenu.LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });
    }

    private void logOut() {
        removeItemFromSharedPreferences(this, getString(R.string.preferences_name_user_preferences), Context.MODE_PRIVATE, getString(R.string.preferences_key_user_info));
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }
}
