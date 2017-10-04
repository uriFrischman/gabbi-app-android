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
import com.frischman.uri.gabbiapp.databinding.FragmentEventListBinding;
import com.frischman.uri.gabbiapp.loader.EventsLoader;
import com.frischman.uri.gabbiapp.model.Event;
import com.frischman.uri.gabbiapp.ui.adapter.EventRecyclerViewAdapter;

import java.util.List;


public class EventListFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Event>> {

    private static final String TAG = "EventListFragment";
    private EventRecyclerViewAdapter mEventRecyclerViewAdapter;

    private FragmentEventListBinding mBinding;

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getSupportLoaderManager().initLoader(0, null, this).forceLoad();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_list, container, false);

        View rootView = mBinding.getRoot();

        initRecyclerView();

        return rootView;
    }

    private void initRecyclerView() {

        mEventRecyclerViewAdapter = new EventRecyclerViewAdapter(getActivity().getApplicationContext(), null);
        mBinding.recyclerViewListOfEvents.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mBinding.recyclerViewListOfEvents.setAdapter(mEventRecyclerViewAdapter);
        mBinding.recyclerViewListOfEvents.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL));
        mEventRecyclerViewAdapter.setItemClickListener(new EventRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d(TAG, "You clicked on event: " + mEventRecyclerViewAdapter.getItem(position).getEventName());
            }
        });

        return rootView;
    }

    @Override
    public Loader<List<Event>> onCreateLoader(int id, Bundle args) {
        return new EventsLoader(getActivity().getApplicationContext(), false);
    }

    @Override
    public void onLoadFinished(Loader<List<Event>> loader, List<Event> data) {
        mEventRecyclerViewAdapter.addListOfEvents(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Event>> loader) {

    }
}
