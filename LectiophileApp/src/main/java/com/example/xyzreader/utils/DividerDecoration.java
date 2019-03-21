package com.example.xyzreader.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DividerDecoration extends RecyclerView.ItemDecoration {

    private int mItemOffset;

    public DividerDecoration(int mItemOffset) {
        this.mItemOffset = mItemOffset;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        outRect.left = mItemOffset;
        outRect.right = mItemOffset;
        outRect.bottom = mItemOffset;

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = mItemOffset;
        }
    }
}
