package com.frischman.uri.gabbiapp.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.databinding.ActivityMainBinding;

import static com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelSlideListener;
import static com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    @Override
    public void onBackPressed() {
        if (mBinding.slidingLayout.getPanelState() == PanelState.EXPANDED) {
            mBinding.slidingLayout.setPanelState(PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initPanelSlideUpMenu();

        mBinding.fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.slidingLayout.setPanelState(PanelState.EXPANDED);
            }
        });

    }

    private void initPanelSlideUpMenu() {

        mBinding.slidingLayout.setPanelHeight(0);

        mBinding.slidingLayout.addPanelSlideListener(new PanelSlideListener() {
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
}
