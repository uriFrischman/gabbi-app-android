package com.frischman.uri.gabbiapp.ui.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.databinding.FragmentEventPopupBinding;
import com.frischman.uri.gabbiapp.loader.AliyahClaimLoader;
import com.frischman.uri.gabbiapp.loader.AliyahsLoader;
import com.frischman.uri.gabbiapp.model.Aliyah;
import com.frischman.uri.gabbiapp.model.User;
import com.frischman.uri.gabbiapp.network.response.ClaimAliyahResponse;
import com.frischman.uri.gabbiapp.ui.RecyclerViewItemClick;
import com.frischman.uri.gabbiapp.ui.adapter.EventPopUpRecyclerViewAdapter;
import com.frischman.uri.gabbiapp.utility.SharedPreferencesUtil;

import java.util.List;

import static com.frischman.uri.gabbiapp.ui.activity.MainActivity.setFabButtonVisibility;
import static com.frischman.uri.gabbiapp.utility.FragmentUtil.removeFragmentFromView;
import static com.frischman.uri.gabbiapp.utility.LoaderUtil.restartLoader;

public class EventPopupFragment extends Fragment {

    private static final String TAG = "EventPopupFragment";

    private static FragmentEventPopupBinding mBinding;
    private final EventPopupFragment mContext = this;

    private String mEventName;
    private List<Aliyah> mAliyahList;

    private final int ALIYAH_LOADER_CALLBACK = 1;
    private final int CLAIM_ALIYAH_LOADER_CALLBACK = 2;
    private EventPopUpRecyclerViewAdapter mEventPopUpRecyclerViewAdapter;

    private LoaderManager.LoaderCallbacks<ClaimAliyahResponse> claimAliyahResponseLoaderCallbacks;
    private LoaderManager.LoaderCallbacks<List<Aliyah>> aliyahLoaderCallback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_popup, container, false);
        mEventName = getArguments().getString(getString(R.string.bundle_argument_event_name));

        initRecyclerView();
        initializeOnClickListeners();

        mBinding.popupTitle.setText(mEventName);
        setFabButtonVisibility(View.GONE);

        initializeAliyahLoaderCallback();
        restartLoader(mContext, ALIYAH_LOADER_CALLBACK, null, aliyahLoaderCallback);

        return mBinding.getRoot();
    }

    private void initRecyclerView() {
        mEventPopUpRecyclerViewAdapter = new EventPopUpRecyclerViewAdapter(getActivity().getApplicationContext());
        mBinding.eventAliyahList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mBinding.eventAliyahList.setAdapter(mEventPopUpRecyclerViewAdapter);
        mBinding.eventAliyahList.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL));
    }

    private void initializeAliyahLoaderCallback() {
        aliyahLoaderCallback = new LoaderManager.LoaderCallbacks<List<Aliyah>>() {
            @Override
            public Loader<List<Aliyah>> onCreateLoader(int id, Bundle args) {
                return new AliyahsLoader(getActivity().getApplicationContext(), mEventName);
            }

            @Override
            public void onLoadFinished(Loader<List<Aliyah>> loader, List<Aliyah> data) {
                mAliyahList = data;
                mEventPopUpRecyclerViewAdapter.addAliyahs(data);
            }

            @Override
            public void onLoaderReset(Loader<List<Aliyah>> loader) {
            }
        };
    }

    private void initializeAliyahClaimLoaderCallback(final int aliyahIndex, final User user, final Aliyah aliyah) {
        claimAliyahResponseLoaderCallbacks = new LoaderManager.LoaderCallbacks<ClaimAliyahResponse>() {
            @Override
            public Loader<ClaimAliyahResponse> onCreateLoader(int id, Bundle args) {
                return new AliyahClaimLoader(getActivity().getApplicationContext(), aliyah, user);
            }

            @Override
            public void onLoadFinished(Loader<ClaimAliyahResponse> loader, ClaimAliyahResponse data) {
                Toast.makeText(getActivity().getApplicationContext(), data.getMessage(), Toast.LENGTH_SHORT).show();
                if(data.isSuccesfullClaim()) {
                    mEventPopUpRecyclerViewAdapter.setAliyah(aliyahIndex, data.getAliyah());
                }
            }

            @Override
            public void onLoaderReset(Loader<ClaimAliyahResponse> loader) {

            }
        };

    }

    private void initializeOnClickListeners() {
        mBinding.buttonEventPopupClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragmentFromView(getActivity().getSupportFragmentManager(), R.id.framelayout_overlay_container);
                setFabButtonVisibility(View.VISIBLE);
            }
        });

        mEventPopUpRecyclerViewAdapter.setItemClickListener(new RecyclerViewItemClick() {
            @Override
            public void onClick(View v, final int position) {
                final Aliyah aliyah = mEventPopUpRecyclerViewAdapter.getAliyah(position);
                final User user = (User) SharedPreferencesUtil.getObjectInSharedPreferences(getActivity().getApplicationContext(), getString(R.string.preferences_name_user_preferences), Context.MODE_PRIVATE, getString(R.string.preferences_key_user_info), User.class);

                initializeAliyahClaimLoaderCallback(position, user, aliyah);
                restartLoader(mContext, CLAIM_ALIYAH_LOADER_CALLBACK, null, claimAliyahResponseLoaderCallbacks);
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        setFabButtonVisibility(View.VISIBLE);
    }
}
