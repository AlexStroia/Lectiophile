package com.example.xyzreader.ui.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import androidx.databinding.BindingAdapter;

public class ImageBindings {

    @BindingAdapter(value = {"imageUrl", "progressBar"},requireAll = false)
    public static void setImageUrl(ImageView view, String url, ProgressBar progressBar) {
        Picasso.get().load(url).into(view, new Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
}
