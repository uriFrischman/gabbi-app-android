package com.frischman.uri.gabbiapp.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.databinding.ViewAliyahRowBinding;
import com.frischman.uri.gabbiapp.model.Aliyah;
import com.frischman.uri.gabbiapp.ui.RecyclerViewItemClick;
import com.frischman.uri.gabbiapp.ui.fragment.GetTextFragment;

import java.util.List;

import static com.frischman.uri.gabbiapp.utility.FragmentUtil.removeFragmentFromView;
import static com.frischman.uri.gabbiapp.utility.FragmentUtil.replaceViewWithFragment;

public class EventPopUpRecyclerViewAdapter extends RecyclerView.Adapter<EventPopUpRecyclerViewAdapter.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<Aliyah> mAliyahList;
    private RecyclerViewItemClick mOnClickListener;
    private FragmentManager mFragmentManager;

    public EventPopUpRecyclerViewAdapter(Context context, FragmentManager fragmentManager) {
        mLayoutInflater = LayoutInflater.from(context);
        mFragmentManager = fragmentManager;
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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ViewAliyahRowBinding mBinding;

        public ViewHolder(final ViewAliyahRowBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.getRoot().setOnClickListener(this);
            mBinding.GetTextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeFragmentFromView(mFragmentManager, R.id.framelayout_overlay_container);
                    Fragment getTextFragment = new GetTextFragment();
                    Bundle args = new Bundle();
                    args.putString("reading", mBinding.getAliyah().getReading());
                    getTextFragment.setArguments(args);
                    replaceViewWithFragment(mFragmentManager, R.id.mainFragment,getTextFragment, true);
                }
            });
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onClick(v, getAdapterPosition());
        }

    }

    public void setItemClickListener(RecyclerViewItemClick itemClickListener) {
        mOnClickListener = itemClickListener;
    }

    public void setAliyah(int index, Aliyah aliyah) {
        mAliyahList.set(index, aliyah);
        this.notifyItemChanged(index);
    }
}
