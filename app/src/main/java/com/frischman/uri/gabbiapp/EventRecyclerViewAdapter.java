package com.frischman.uri.gabbiapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frischman.uri.gabbiapp.model.Event;

import java.util.List;


public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.ViewHolder> {

    private List<Event> mEventList;
    private LayoutInflater mLayoutInflater;
    private ItemClickListener mItemClickListener;

    public EventRecyclerViewAdapter(Context context, List<Event> eventList) {
        mLayoutInflater = LayoutInflater.from(context);
        mEventList = eventList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.event_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String eventName = mEventList.get(position).getEventName();
        holder.mEventName.setText(eventName);
        holder.mNumAliyahsLeft.setText(String.valueOf(mEventList.get(position).getNumberOfAliyahs() - mEventList.get(position).getNumberOfAliyahsTaken()));

    }

    public Event getItem(int position) {
        return mEventList.get(position);
    }

    public void addListOfEvents(List<Event> listOfEvents){
        mEventList = listOfEvents;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mEventList != null) {
            return mEventList.size();
        } else {
            return 0;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView mEventName;
        public TextView mNumAliyahsLeft;

        public ViewHolder(View v) {
            super(v);
            mEventName = (TextView) v.findViewById(R.id.eventName);
            v.setOnClickListener(this);

            mNumAliyahsLeft = (TextView) v.findViewById(R.id.numAliyahsLeft);

        }


        @Override
        public void onClick(View v) {
            mItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
