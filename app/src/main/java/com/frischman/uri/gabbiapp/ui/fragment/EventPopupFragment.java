package com.frischman.uri.gabbiapp.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.databinding.FragmentEventPopupBinding;
import com.frischman.uri.gabbiapp.loader.AliyahLoader;
import com.frischman.uri.gabbiapp.model.Aliyah;
import com.frischman.uri.gabbiapp.model.User;
import com.frischman.uri.gabbiapp.ui.RecyclerViewItemClick;
import com.frischman.uri.gabbiapp.ui.adapter.EventPopUpRecyclerViewAdapter;

import java.util.List;
import java.util.Random;

import static com.frischman.uri.gabbiapp.ui.activity.MainActivity.getActivityMainBinding;
import static com.frischman.uri.gabbiapp.utility.AliyahUtil.claimAliyah;
import static com.frischman.uri.gabbiapp.utility.AliyahUtil.isAliyahTaken;
import static com.frischman.uri.gabbiapp.utility.FragmentUtil.removeFragmentFromView;

public class EventPopupFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Aliyah>> {

    private static final String TAG = "EventPopupFragment";

    private static FragmentEventPopupBinding mBinding;

    private String mEventName;
    private List<Aliyah> mAliyahList;

    private EventPopUpRecyclerViewAdapter mEventPopUpRecyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_popup, container, false);
        mEventName = getArguments().getString("eventName");

        initRecyclerView();

        mBinding.popupTitle.setText(mEventName);
        getActivityMainBinding().fabButton.setVisibility(View.GONE);

        getActivity().getSupportLoaderManager().initLoader(new Random().nextInt(), null, this).forceLoad();

        mBinding.buttonEventPopupClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragmentFromView(getActivity().getSupportFragmentManager(), R.id.framelayout_overlay_container);
                getActivityMainBinding().fabButton.setVisibility(View.VISIBLE);
            }
        });

        return mBinding.getRoot();
    }

    private void initRecyclerView() {
        mEventPopUpRecyclerViewAdapter = new EventPopUpRecyclerViewAdapter(getActivity().getApplicationContext());
        mBinding.eventAliyahList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mBinding.eventAliyahList.setAdapter(mEventPopUpRecyclerViewAdapter);
        mBinding.eventAliyahList.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL));
        mEventPopUpRecyclerViewAdapter.setItemClickListener(new RecyclerViewItemClick() {
            @Override
            public void onClick(View v, int position) {
                Aliyah aliyah = mEventPopUpRecyclerViewAdapter.getAliyah(position);
                if (!isAliyahTaken(aliyah)) {
                    User user = new User(1, "Uri", "Frischman", true);
                    claimAliyah(user, aliyah);
                } else {
                    Log.d(TAG, "onClick: The aliyah is already taken");
                }
            }
        });
    }

    @Override
    public Loader<List<Aliyah>> onCreateLoader(int id, Bundle args) {
        return new AliyahLoader(getActivity().getApplicationContext(), mEventName);
    }

    @Override
    public void onLoadFinished(Loader<List<Aliyah>> loader, List<Aliyah> data) {
        mAliyahList = data;
        mEventPopUpRecyclerViewAdapter.addAliyahs(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Aliyah>> loader) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivityMainBinding().fabButton.setVisibility(View.VISIBLE);
    }
}
