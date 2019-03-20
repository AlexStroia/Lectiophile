package com.example.xyzreader.utils;

import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;


public class DecorationUtils extends RecyclerView.ItemDecoration{

    private final int mItemDecorationOffset;

    public DecorationUtils(int mItemDecoration) {
        this.mItemDecorationOffset = mItemDecoration;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.left = mItemDecorationOffset;
        outRect.right = mItemDecorationOffset;
        outRect.bottom = mItemDecorationOffset;
        outRect.top = mItemDecorationOffset;
    }
}
