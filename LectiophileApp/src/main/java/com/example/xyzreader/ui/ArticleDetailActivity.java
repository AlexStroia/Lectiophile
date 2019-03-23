package com.example.xyzreader.ui;

import android.os.Bundle;
import android.util.Log;

import com.example.xyzreader.R;
import com.example.xyzreader.model.BookViewModel;
import com.example.xyzreader.viewmodel.ArticleDetailViewModel;
import com.example.xyzreader.viewmodel.factory.ViewModelFactory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

/**
 * An activity representing a single Article detail screen, letting you swipe between articles.
 */
public class ArticleDetailActivity extends AppCompatActivity {

    private static final String TAG = "ArticleDetailActivity";

    private ArticleDetailViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent() != null) {
            int bookId = getIntent().getIntExtra(getString(R.string.book_click), 0);
            initView(bookId);
            Log.d(TAG, "onCreate: " + bookId);
        }
    }

    private void initView(int id) {
        ViewModelFactory factory = new ViewModelFactory(this.getApplication(), id);
        vm = ViewModelProviders.of(this, factory).get(ArticleDetailViewModel.class);
        vm.getBookId().observe(this, book -> {
            Log.d(TAG, "initView: " + book.toString());
        });
    }
}
