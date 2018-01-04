package com.frischman.uri.gabbiapp.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.databinding.FragmentEventListBinding;
import com.frischman.uri.gabbiapp.loader.GetEventsLoader;
import com.frischman.uri.gabbiapp.model.Event;
import com.frischman.uri.gabbiapp.network.response.GetEventsResponse;
import com.frischman.uri.gabbiapp.ui.RecyclerViewItemClick;
import com.frischman.uri.gabbiapp.ui.adapter.EventRecyclerViewAdapter;
import com.frischman.uri.gabbiapp.ui.listener.HidingScrollListener;

import java.util.ArrayList;
import java.util.List;

import static com.frischman.uri.gabbiapp.utility.FragmentUtil.checkIfViewHasFragment;
import static com.frischman.uri.gabbiapp.utility.FragmentUtil.replaceViewWithFragment;

public class EventListFragment extends Fragment implements SearchView.OnQueryTextListener {

    private static final String TAG = "EventListFragment";
    private EventRecyclerViewAdapter mEventRecyclerViewAdapter;

    private FragmentEventListBinding mBinding;

    private LoaderManager.LoaderCallbacks<GetEventsResponse> getEventsResponseLoaderCallbacks;

    @Override
    public void onResume() {
        super.onResume();

        getEventsResponseLoaderCallbacks = new LoaderManager.LoaderCallbacks<GetEventsResponse>() {
            @Override
            public Loader<GetEventsResponse> onCreateLoader(int id, Bundle args) {
                mBinding.eventListProgressText.setVisibility(View.VISIBLE);
                return new GetEventsLoader(getActivity().getApplicationContext(), false);
            }

            @Override
            public void onLoadFinished(Loader<GetEventsResponse> loader, GetEventsResponse data) {
                Log.d(TAG, "onLoadFinished: " + data.getListOfEvents());
                mEventRecyclerViewAdapter.initUnFilteredList(data.getListOfEvents());
                mEventRecyclerViewAdapter.addListOfEvents(data.getListOfEvents());
                mBinding.eventListProgressText.setVisibility(View.GONE);
                mBinding.eventSearch.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoaderReset(Loader<GetEventsResponse> loader) {

            }
        };

        getActivity().getSupportLoaderManager().initLoader(0, null, getEventsResponseLoaderCallbacks).forceLoad();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_list, container, false);

        View rootView = mBinding.getRoot();

        initRecyclerView();
        initSearchView();

        return rootView;
    }

    private void initRecyclerView() {

        mEventRecyclerViewAdapter = new EventRecyclerViewAdapter(getActivity().getApplicationContext());
        mBinding.recyclerViewListOfEvents.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mBinding.recyclerViewListOfEvents.setAdapter(mEventRecyclerViewAdapter);
        mBinding.recyclerViewListOfEvents.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL));
        mEventRecyclerViewAdapter.setItemClickListener(new RecyclerViewItemClick() {
            @Override
            public void onClick(View v, int position) {
                if (checkIfViewHasFragment(getActivity().getSupportFragmentManager(), R.id.framelayout_overlay_container)) {
                    Toast.makeText(getActivity().getApplicationContext(), "Please close the menu", Toast.LENGTH_SHORT).show();
                } else {
                    String eventName = mEventRecyclerViewAdapter.getItem(position).getEventName();
                    Log.d(TAG, "You clicked on event: " + eventName);
                    Bundle args = new Bundle();
                    args.putString(getString(R.string.bundle_argument_event_name), eventName);
                    EventPopupFragment fragment = new EventPopupFragment();
                    fragment.setArguments(args);
                    replaceViewWithFragment(getActivity().getSupportFragmentManager(), R.id.framelayout_overlay_container, fragment, false);
                }
            }
        });
        mBinding.recyclerViewListOfEvents.setOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
            }

            @Override
            public void onShow() {
                mBinding.eventSearchContainer.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
                mBinding.recyclerViewListOfEvents.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
            }
        });

    }

    private void initSearchView() {
        mBinding.eventSearch.setOnQueryTextListener(this);
        mBinding.eventSearch.setVisibility(View.GONE);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<Event> eventList = filterEventList(newText);
        mEventRecyclerViewAdapter.addListOfEvents(eventList);
        mEventRecyclerViewAdapter.notifyDataSetChanged();
        return true;
    }

    private List<Event> filterEventList(String query) {

        List<Event> queriedList = new ArrayList<>();

        if (query.length() != 0) {

            if (query.length() > mBinding.eventSearch.getQuery().length()) {
                addQueriedEventsToList(mEventRecyclerViewAdapter.getEventList(), queriedList, query);

            } else {
                addQueriedEventsToList(mEventRecyclerViewAdapter.getUnfilteredList(), queriedList, query);
            }

            return queriedList;

        } else {
            return  mEventRecyclerViewAdapter.getUnfilteredList();
        }
    }

    private void addQueriedEventsToList(List<Event> eventList, List<Event> listToAddEvent, String query) {
        for (Event event : eventList) {
            if (event.getEventName().toLowerCase().contains(query.toLowerCase())) {
                listToAddEvent.add(event);
            }
        }
    }
}