package com.example.xyzreader.ui.view;

import com.example.xyzreader.adapter.FragmentArticleDetailBodyAdapter;
import com.example.xyzreader.adapter.LectiophileAdapter;
import com.example.xyzreader.model.BodyViewModel;
import com.example.xyzreader.model.BookViewModel;
import com.example.xyzreader.utils.DividerDecoration;

import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class RecyclerBindings {
    private static final String TAG = "RecyclerBindings";

    @BindingAdapter("items")
    public static void addFeedItems(RecyclerView recyclerView, List<BookViewModel> books) {
        LectiophileAdapter adapter;
        if (recyclerView.getAdapter() == null) {
            adapter = new LectiophileAdapter();
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            recyclerView.addItemDecoration(new DividerDecoration(8));
            recyclerView.setAdapter(adapter);
        }
        adapter = (LectiophileAdapter) recyclerView.getAdapter();
        adapter.setData(books);
    }

    @BindingAdapter("items")
    public static void addContentItems(RecyclerView recyclerView, List<BodyViewModel> body) {
        FragmentArticleDetailBodyAdapter adapter;
        if (recyclerView.getAdapter() == null) {
            adapter = new FragmentArticleDetailBodyAdapter();
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            recyclerView.setAdapter(adapter);
        }
        adapter = (FragmentArticleDetailBodyAdapter) recyclerView.getAdapter();
        adapter.setData(body);
    }
}
