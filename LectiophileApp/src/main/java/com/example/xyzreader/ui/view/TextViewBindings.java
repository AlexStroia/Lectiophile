package com.example.xyzreader.ui.view;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.BindingAdapter;

public class TextViewBindings {


    @BindingAdapter(value = {"full_text", "progressBar"}, requireAll = false)
    public static void setText(TextView textView, String body, ProgressBar progressBar) {
        textView.post(() -> {
            textView.setText(body);
            progressBar.setVisibility(View.GONE);
        });
    }
}
