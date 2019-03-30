package com.example.xyzreader.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.xyzreader.databinding.ListBodyItemBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentArticleDetailBodyAdapter extends RecyclerView.Adapter<FragmentArticleDetailBodyAdapter.FragmentArticleDetailAdapterViewHolder> {

    private List<String> mBody;
    private static final String TAG = "FragmentArticleDetailBo";

    public FragmentArticleDetailBodyAdapter() {
        this.mBody = new ArrayList<>();
    }

    @NonNull
    @Override
    public FragmentArticleDetailAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListBodyItemBinding binding = ListBodyItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new FragmentArticleDetailAdapterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FragmentArticleDetailAdapterViewHolder holder, int position) {
        holder.bind(mBody.get(position));
    }

    @Override
    public int getItemCount() {
        if (mBody == null) return 0;
        return mBody.size();
    }

    public void setData(List<String> body) {
        this.mBody = body;
        notifyDataSetChanged();
    }

    static class FragmentArticleDetailAdapterViewHolder extends RecyclerView.ViewHolder {

        private ListBodyItemBinding mBinding;

        FragmentArticleDetailAdapterViewHolder(ListBodyItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        void bind(String content) {
            mBinding.tvArticleBody.setText(content);
        }
    }
}
