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

import static com.frischman.uri.gabbiapp.loader.LoaderConstants.NON_SHABBOS_ZMANIM_LOADER_ID;
import static com.frischman.uri.gabbiapp.loader.LoaderConstants.SHABBOS_ZMANIM_LOADER_ID;

public class ZmanimFragment extends Fragment {

    private final int GEO_NAME_ID = 1;
    private FragmentZmanimBinding mBinding;
    private LoaderManager.LoaderCallbacks<ShabbosZmanimResponse> mShabbosZmanimResponseLoaderCallbacks;
    private LoaderManager.LoaderCallbacks<NonShabbosZmanimResponse> mNonShabbosZmanimResponseLoaderCallbacks;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_zmanim, container, false);

        loadNonShabbosZmanim();
        loadShabbosZmanim(GEO_NAME_ID);

        return mBinding.getRoot();
    }

    private void initializeShabbosZmanimLoaderCallback(final int geoNameId) {
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

    private void loadShabbosZmanim(int geoNameId) {
        initializeShabbosZmanimLoaderCallback(geoNameId);
        getActivity().getSupportLoaderManager().initLoader(SHABBOS_ZMANIM_LOADER_ID, null, mShabbosZmanimResponseLoaderCallbacks).forceLoad();
    }

    private void loadNonShabbosZmanim() {
        initializeNonShabbosZmanimLoaderCallback();
        getActivity().getSupportLoaderManager().initLoader(NON_SHABBOS_ZMANIM_LOADER_ID, null, mNonShabbosZmanimResponseLoaderCallbacks).forceLoad();
    }
}
