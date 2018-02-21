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
import com.frischman.uri.gabbiapp.loader.ShabbosZmanimLoader;
import com.frischman.uri.gabbiapp.network.response.ShabbosZmanimResponse;

public class ZmanimFragment extends Fragment {

    private FragmentZmanimBinding mBinding;
    private static final String TAG = "ZmanimFragment";

    private LoaderManager.LoaderCallbacks<ShabbosZmanimResponse> mShabbosZmanimResponseLoaderCallbacks;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_zmanim, container, false);

        final int geoNameId = 1;


        mShabbosZmanimResponseLoaderCallbacks = new LoaderManager.LoaderCallbacks<ShabbosZmanimResponse>() {
            @Override
            public Loader<ShabbosZmanimResponse> onCreateLoader(int id, Bundle args) {
                return new ShabbosZmanimLoader(getActivity().getApplicationContext(), geoNameId);
            }

            @Override
            public void onLoadFinished(Loader<ShabbosZmanimResponse> loader, ShabbosZmanimResponse data) {
                mBinding.setZmanimCandlesAndHavdalah(data);
            }

            @Override
            public void onLoaderReset(Loader<ShabbosZmanimResponse> loader) {

            }
        };

        getActivity().getSupportLoaderManager().initLoader(100, null, mShabbosZmanimResponseLoaderCallbacks).forceLoad();


        return mBinding.getRoot();
    }
}
