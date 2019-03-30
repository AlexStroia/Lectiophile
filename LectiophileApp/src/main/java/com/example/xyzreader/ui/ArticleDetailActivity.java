package com.example.xyzreader.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.xyzreader.R;
import com.example.xyzreader.adapter.FragmentArticleDetailBodyAdapter;
import com.example.xyzreader.databinding.ActivityArticleDetailBinding;
import com.example.xyzreader.viewmodel.ArticleDetailViewModel;
import com.example.xyzreader.viewmodel.factory.ViewModelFactory;

import java.util.List;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import co.alexdev.data.model.Book;


public class ArticleDetailActivity extends AppCompatActivity {

    private static final String TAG = "ArticleDetailActivity";

    private ArticleDetailViewModel vm;
    private ActivityArticleDetailBinding mBinding;
    private FragmentArticleDetailBodyAdapter adapter;

    private int mBookId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_article_detail);
        mBinding.setLifecycleOwner(this);

        if (getIntent() != null) {
            mBookId = getIntent().getIntExtra(getString(R.string.book_click), 0);
            initView(mBookId);
            Log.d(TAG, "onCreate: " + mBookId);
        }
    }

    private void initView(int id) {
        ViewModelFactory factory = new ViewModelFactory(this.getApplication(), id);
        vm = ViewModelProviders.of(this, factory).get(ArticleDetailViewModel.class);
        LiveData<List<Integer>> mIntegerIdsLiveData = vm.getBooksIdsLiveData();
        mIntegerIdsLiveData.observe(this, idList -> {
            mIntegerIdsLiveData.removeObservers(this);

            LiveData<Book> bookLiveData = vm.getBook();
            bookLiveData.observe(this, book -> {
                bookLiveData.removeObservers(this);
                vm.mapToBookViewModel(bookLiveData.getValue());
                vm.parseDataToParagraph(book.getBody());
            });

            vm.getBodyContentLiveData().observe(this, body -> {
                adapter = new FragmentArticleDetailBodyAdapter();
                mBinding.rvBody.setLayoutManager(new LinearLayoutManager(this));
                mBinding.rvBody.setAdapter(adapter);
                adapter.setData(body);
            });
        });


        mBinding.shareFab.setOnClickListener(view -> startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(this)
                .setType(getString(R.string.share_type))
                .setText(getString(R.string.share_data))
                .getIntent(), getString(R.string.action_share))));

        mBinding.ibBack.setOnClickListener(view -> {
            finishAfterTransition();
            finish();
        });
    }
}