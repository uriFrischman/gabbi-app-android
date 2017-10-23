package com.frischman.uri.gabbiapp.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.databinding.ViewAliyahRowBinding;
import com.frischman.uri.gabbiapp.model.Aliyah;
import com.frischman.uri.gabbiapp.ui.RecyclerViewItemClick;
import com.frischman.uri.gabbiapp.utility.IntegerUtil;

import java.util.List;

public class EventPopUpRecyclerViewAdapter extends RecyclerView.Adapter<EventPopUpRecyclerViewAdapter.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<Aliyah> mAliyahList;
    private IntegerUtil mIntegerUtil;
    private RecyclerViewItemClick mOnClickListener;

    public EventPopUpRecyclerViewAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mIntegerUtil = new IntegerUtil();
    }

    @Override
    public EventPopUpRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewAliyahRowBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.view_aliyah_row, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mBinding.setAliyah(mAliyahList.get(position));
        holder.mBinding.setIntUtil(mIntegerUtil);
    }

    public void addAliyahs(List<Aliyah> aliyahList) {
        mAliyahList = aliyahList;
        this.notifyDataSetChanged();
    }

    public Aliyah getAliyah(int index) {
        return mAliyahList.get(index);
    }

    @Override
    public int getItemCount() {
        return mAliyahList == null ? 0 : mAliyahList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ViewAliyahRowBinding mBinding;

        public ViewHolder(ViewAliyahRowBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onClick(v, getAdapterPosition());
        }

    }

    public void setItemClickListener(RecyclerViewItemClick itemClickListener) {
        mOnClickListener = itemClickListener;
    }
}
