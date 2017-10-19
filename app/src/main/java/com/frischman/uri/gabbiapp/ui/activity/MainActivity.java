package com.frischman.uri.gabbiapp.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.databinding.ActivityMainBinding;
import com.frischman.uri.gabbiapp.ui.fragment.EventListFragment;
import com.frischman.uri.gabbiapp.ui.fragment.ZmanimFragment;

import static com.frischman.uri.gabbiapp.utility.FragmentUtil.replaceViewWithFragment;
import static com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelSlideListener;
import static com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState;

public class MainActivity extends AppCompatActivity {

    private static ActivityMainBinding mBinding;

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
        replaceViewWithFragment(getSupportFragmentManager(), R.id.mainFragment, new EventListFragment(), false);

        initPanelSlideUpMenu();
        initMenuButtons();

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
                mBinding.slidingLayout.setPanelState(PanelState.COLLAPSED);
                replaceViewWithFragment(getSupportFragmentManager(), R.id.mainFragment, new EventListFragment(), true);
            }
        });

        mBinding.viewMenu.CloseMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.slidingLayout.setPanelState(PanelState.COLLAPSED);
            }
        });

        mBinding.viewMenu.ZmanimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.slidingLayout.setPanelState(PanelState.COLLAPSED);
                replaceViewWithFragment(getSupportFragmentManager(), R.id.mainFragment, new ZmanimFragment(), true);
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

    public static ActivityMainBinding getActivityMainBinding() {
        return mBinding;
    }
}
