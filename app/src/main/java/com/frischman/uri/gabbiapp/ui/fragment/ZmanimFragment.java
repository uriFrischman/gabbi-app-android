package com.frischman.uri.gabbiapp.ui.fragment;

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
import com.frischman.uri.gabbiapp.databinding.FragmentZmanimBinding;
import com.frischman.uri.gabbiapp.loader.NonShabbosZmanimLoader;
import com.frischman.uri.gabbiapp.loader.ShabbosZmanimLoader;
import com.frischman.uri.gabbiapp.network.response.NonShabbosZmanimResponse;
import com.frischman.uri.gabbiapp.network.response.ShabbosZmanimResponse;

public class ZmanimFragment extends Fragment {

    private final int SHABBOS_ZMANIM_LOADER_CALLBACK = 1;
    private final int NON_SHABBOS_ZMANIM_LOADER_CALLBACK = 2;
    private FragmentZmanimBinding mBinding;
    private LoaderManager.LoaderCallbacks<ShabbosZmanimResponse> mShabbosZmanimResponseLoaderCallbacks;
    private LoaderManager.LoaderCallbacks<NonShabbosZmanimResponse> mNonShabbosZmanimResponseLoaderCallbacks;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_zmanim, container, false);

        loadNonShabbosZmanim();
        loadShabbosZmanim();

        return mBinding.getRoot();
    }

    private void initializeShabbosZmanimLoaderCallback() {

        final int geoNameId = 1;

        mShabbosZmanimResponseLoaderCallbacks = new LoaderManager.LoaderCallbacks<ShabbosZmanimResponse>() {
            @Override
            public Loader<ShabbosZmanimResponse> onCreateLoader(int id, Bundle args) {
                return new ShabbosZmanimLoader(getActivity().getApplicationContext(), geoNameId);
            }

            @Override
            public void onLoadFinished(Loader<ShabbosZmanimResponse> loader, ShabbosZmanimResponse data) {
                mBinding.shabbosZmanim.setShabbosZmanim(data.getShabbosZmanim());
            }

            @Override
            public void onLoaderReset(Loader<ShabbosZmanimResponse> loader) {

            }
        };
    }

    private void initializeNonShabbosZmanimLoaderCallback() {
        mNonShabbosZmanimResponseLoaderCallbacks = new LoaderManager.LoaderCallbacks<NonShabbosZmanimResponse>() {
            @Override
            public Loader<NonShabbosZmanimResponse> onCreateLoader(int id, Bundle args) {
                return new NonShabbosZmanimLoader(getActivity().getApplicationContext());
            }

            @Override
            public void onLoadFinished(Loader<NonShabbosZmanimResponse> loader, NonShabbosZmanimResponse data) {
                mBinding.nonShabbosZmaim.setNonShabbosZmanim(data.getNonShabbosZmanim());
            }

            @Override
            public void onLoaderReset(Loader<NonShabbosZmanimResponse> loader) {

            }
        };
    }

    private void loadShabbosZmanim() {
        initializeShabbosZmanimLoaderCallback();
        getActivity().getSupportLoaderManager().initLoader(SHABBOS_ZMANIM_LOADER_CALLBACK, null, mShabbosZmanimResponseLoaderCallbacks).forceLoad();
    }

    private void loadNonShabbosZmanim() {
        initializeNonShabbosZmanimLoaderCallback();
        getActivity().getSupportLoaderManager().initLoader(NON_SHABBOS_ZMANIM_LOADER_CALLBACK, null, mNonShabbosZmanimResponseLoaderCallbacks).forceLoad();
    }
}
