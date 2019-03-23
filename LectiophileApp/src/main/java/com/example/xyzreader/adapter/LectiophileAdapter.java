package com.example.xyzreader.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xyzreader.databinding.ListItemArticleBinding;
import com.example.xyzreader.model.BookViewModel;
import com.example.xyzreader.utils.listeners.OnBookSelectedListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LectiophileAdapter extends RecyclerView.Adapter<LectiophileAdapter.ItemListViewHolder> {

    private static List<BookViewModel> mBooks;
    private static OnBookSelectedListener mListener;

    public LectiophileAdapter() {
        this.mBooks = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemArticleBinding binder = ListItemArticleBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ItemListViewHolder(binder);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListViewHolder holder, int position) {
        holder.mBinding.setViewModel(mBooks.get(position));
    }

    public void setData(List<BookViewModel> books) {
        mBooks = books;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mBooks == null) return 0;
        return mBooks.size();
    }

    public void setListener(OnBookSelectedListener listener) {
        if (mListener == null) {
            mListener = listener;
        }
    }

    static class ItemListViewHolder extends RecyclerView.ViewHolder {

        private ListItemArticleBinding mBinding;
        private static final String TAG = "ItemListViewHolder";

        ItemListViewHolder(@NonNull ListItemArticleBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;

            this.mBinding.getRoot().setOnClickListener(view -> {
                int position = getAdapterPosition();

                if (mListener != null) {
                    mListener.onBookItemClick(mBooks.get(position).getId().get());
                }
            });
        }
    }
}
