package com.frischman.uri.gabbiapp.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.databinding.FragmentEventPopupBinding;
import com.frischman.uri.gabbiapp.loader.AliyahLoader;
import com.frischman.uri.gabbiapp.model.Aliyah;

import java.util.List;
import java.util.Random;

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

        getActivity().getSupportLoaderManager().initLoader(new Random().nextInt(), null, this).forceLoad();

        mBinding.buttonEventPopupClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragmentFromView(getActivity().getSupportFragmentManager(), R.id.framelayout_overlay_container);
            }
        });

        return mBinding.getRoot();
    }

    private void initRecyclerView() {
        mEventPopUpRecyclerViewAdapter = new EventPopUpRecyclerViewAdapter(getActivity().getApplicationContext());
        mBinding.eventAliyahList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mBinding.eventAliyahList.setAdapter(mEventPopUpRecyclerViewAdapter);
        mBinding.eventAliyahList.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL));
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
}
