package com.frischman.uri.gabbiapp.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.databinding.ViewAliyahRowBinding;
import com.frischman.uri.gabbiapp.model.Aliyah;

import java.util.List;

public class EventPopUpRecyclerViewAdapter extends RecyclerView.Adapter<EventPopUpRecyclerViewAdapter.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<Aliyah> mAliyahList;

    public EventPopUpRecyclerViewAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public EventPopUpRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewAliyahRowBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.view_aliyah_row, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mBinding.setAliyah(mAliyahList.get(position));

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

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewAliyahRowBinding mBinding;

        public ViewHolder(ViewAliyahRowBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
