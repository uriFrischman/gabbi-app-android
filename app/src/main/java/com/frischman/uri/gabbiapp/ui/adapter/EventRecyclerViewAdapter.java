package com.frischman.uri.gabbiapp.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.databinding.ViewEventRowBinding;
import com.frischman.uri.gabbiapp.model.Event;
import com.frischman.uri.gabbiapp.ui.RecyclerViewItemClick;

import java.util.List;

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.ViewHolder> {

    private List<Event> mEventList;
    private List<Event> mUnfilteredEventList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewItemClick mItemClickListener;

    public EventRecyclerViewAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewEventRowBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.view_event_row, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String eventName = mEventList.get(position).getEventName();
        String numAliyahsLeft = String.valueOf(mEventList.get(position).getNumberOfAliyahs() - mEventList.get(position).getNumberOfAliyahsTaken());
        String eventDate = mEventList.get(position).getEventDate();

        holder.mBinding.eventName.setText(eventName);
        holder.mBinding.numAliyahsLeft.setText(numAliyahsLeft);
        holder.mBinding.eventDate.setText(eventDate);
    }

    public Event getItem(int position) {
        return mEventList.get(position);
    }

    public void addListOfEvents(List<Event> listOfEvents) {
        mEventList = listOfEvents;
        this.notifyDataSetChanged();
    }

    public void initUnFilteredList(List<Event> unfilteredEventList) {
        mUnfilteredEventList = unfilteredEventList;
    }

    public List<Event> getUnfilteredList() {
        return mUnfilteredEventList;
    }

    @Override
    public int getItemCount() {
        return mEventList != null ? mEventList.size() : 0;
    }

    public List<Event> getEventList() {
        return mEventList;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ViewEventRowBinding mBinding;

        ViewHolder(ViewEventRowBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onClick(v, getAdapterPosition());
        }
    }

    public void setItemClickListener(RecyclerViewItemClick itemClickListener) {
        mItemClickListener = itemClickListener;
    }

}
