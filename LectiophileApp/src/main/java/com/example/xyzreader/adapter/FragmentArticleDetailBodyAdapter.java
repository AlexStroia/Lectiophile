package com.example.xyzreader.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentArticleDetailBodyAdapter extends RecyclerView.Adapter<FragmentArticleDetailBodyAdapter.FragmentArticleDetailAdapterViewHolder> {


    @NonNull
    @Override
    public FragmentArticleDetailAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FragmentArticleDetailAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class FragmentArticleDetailAdapterViewHolder extends RecyclerView.ViewHolder {

        public FragmentArticleDetailAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
