package com.example.xyzreader.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xyzreader.databinding.ListItemArticleBinding;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import co.alexdev.data.model.Book;

public class LectiophileAdapter extends RecyclerView.Adapter<LectiophileAdapter.ItemListViewHolder> {

    private List<Book> mBooks;

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
        holder.bind(mBooks.get(position));
    }

    public void setData(List<Book> books) {
        this.mBooks = books;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mBooks == null) return 0;
        return mBooks.size();
    }

    static class ItemListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ListItemArticleBinding mBinding;

        ItemListViewHolder(@NonNull ListItemArticleBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        void bind(co.alexdev.data.model.Book book) {
            mBinding.articleTitle.setText(book.getTitle());
            mBinding.articleSubtitle.setText(book.getPublished_date());
            Picasso.get().load(book.getThumb()).into(mBinding.thumbnail, new Callback() {
                @Override
                public void onSuccess() {
                    mBinding.pbImage.setVisibility(View.GONE);
                }

                @Override
                public void onError(Exception e) {

                }
            });
        }

        @Override
        public void onClick(View view) {

        }
    }
}
