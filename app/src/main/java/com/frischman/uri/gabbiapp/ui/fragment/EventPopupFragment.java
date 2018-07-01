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
import com.frischman.uri.gabbiapp.loader.GetAliyahsLoader;
import com.frischman.uri.gabbiapp.model.Aliyah;
import com.frischman.uri.gabbiapp.model.User;
import com.frischman.uri.gabbiapp.network.response.ClaimAliyahResponse;
import com.frischman.uri.gabbiapp.network.response.GetAliyahsResponse;
import com.frischman.uri.gabbiapp.ui.RecyclerViewItemClick;
import com.frischman.uri.gabbiapp.ui.adapter.EventPopUpRecyclerViewAdapter;
import com.frischman.uri.gabbiapp.utility.SharedPreferencesUtil;

import static com.frischman.uri.gabbiapp.loader.LoaderConstants.ALIYAH_CLAIM_LOADER_ID;
import static com.frischman.uri.gabbiapp.loader.LoaderConstants.GET_ALIYAHS_LOADER_ID;
import static com.frischman.uri.gabbiapp.ui.activity.MainActivity.setFabButtonVisibility;
import static com.frischman.uri.gabbiapp.utility.FragmentUtil.removeFragmentFromView;
import static com.frischman.uri.gabbiapp.utility.LoaderUtil.restartLoader;

public class EventPopupFragment extends Fragment {

    private static FragmentEventPopupBinding mBinding;
    private final EventPopupFragment mContext = this;

    private String mEventName;

    private EventPopUpRecyclerViewAdapter mEventPopUpRecyclerViewAdapter;

    private LoaderManager.LoaderCallbacks<ClaimAliyahResponse> claimAliyahResponseLoaderCallbacks;
    private LoaderManager.LoaderCallbacks<GetAliyahsResponse> aliyahsLoaderCallback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_popup, container, false);


        mEventName = getArguments().getString(getString(R.string.bundle_argument_event_name));
        initializeTitle(mEventName);

        initializeRecyclerView();
        initializeOnClickListeners();

        loadAliyahs();

        return mBinding.getRoot();
    }

    private void initializeRecyclerView() {
        mEventPopUpRecyclerViewAdapter = new EventPopUpRecyclerViewAdapter(getActivity().getApplicationContext(), getFragmentManager());
        mBinding.eventAliyahList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mBinding.eventAliyahList.setAdapter(mEventPopUpRecyclerViewAdapter);
        mBinding.eventAliyahList.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL));
    }

    private void initializeAliyahLoaderCallback() {
        aliyahsLoaderCallback = new LoaderManager.LoaderCallbacks<GetAliyahsResponse>() {
            @Override
            public Loader<GetAliyahsResponse> onCreateLoader(int id, Bundle args) {
                return new GetAliyahsLoader(getActivity().getApplicationContext(), mEventName);
            }

            @Override
            public void onLoadFinished(Loader<GetAliyahsResponse> loader, GetAliyahsResponse data) {
                mEventPopUpRecyclerViewAdapter.addAliyahs(data.getAliyahList());
            }

            @Override
            public void onLoaderReset(Loader<GetAliyahsResponse> loader) {

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
                closePopup();
            }
        });

        mEventPopUpRecyclerViewAdapter.setItemClickListener(new RecyclerViewItemClick() {
            @Override
            public void onClick(View v, int position) {
                Aliyah aliyah = mEventPopUpRecyclerViewAdapter.getAliyah(position);
                User user = (User) SharedPreferencesUtil.getObjectInSharedPreferences(getActivity().getApplicationContext(), getString(R.string.preferences_name_user_preferences), Context.MODE_PRIVATE, getString(R.string.preferences_key_user_info), User.class);

                claimAliyah(position, aliyah, user);
            }
        });
    }

    private void loadAliyahs() {
        initializeAliyahLoaderCallback();
        restartLoader(mContext, GET_ALIYAHS_LOADER_ID, null, aliyahsLoaderCallback);
    }

    private void claimAliyah(int aliyahIndex, Aliyah aliyah, User user) {
        initializeAliyahClaimLoaderCallback(aliyahIndex, user, aliyah);
        restartLoader(mContext, ALIYAH_CLAIM_LOADER_ID, null, claimAliyahResponseLoaderCallbacks);
    }

    private void initializeTitle(String eventName) {
        mBinding.popupTitle.setText(eventName);
    }

    private void closePopup() {
        removeFragmentFromView(getActivity().getSupportFragmentManager(), R.id.framelayout_overlay_container);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}