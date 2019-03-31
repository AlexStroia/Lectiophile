package com.example.xyzreader.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.PersistableBundle;

import com.example.xyzreader.R;
import com.example.xyzreader.adapter.FragmentArticleDetailBodyAdapter;
import com.example.xyzreader.databinding.ActivityArticleDetailBinding;
import com.example.xyzreader.utils.TransitionUtils;
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

    private ArticleDetailViewModel vm;
    private ActivityArticleDetailBinding mBinding;
    private FragmentArticleDetailBodyAdapter adapter;
    private LinearLayoutManager mLayout;
    private Parcelable mScrollPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_article_detail);
        TransitionUtils.setTransition(this);

        if (getIntent() != null) {
            int mBookId = getIntent().getIntExtra(getString(R.string.book_click), 0);
            ViewModelFactory factory = new ViewModelFactory(this.getApplication(), mBookId);
            vm = ViewModelProviders.of(this, factory).get(ArticleDetailViewModel.class);
            mBinding.setLifecycleOwner(this);
            initView();
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mScrollPos = mLayout.onSaveInstanceState();
        outState.putParcelable(getString(R.string.scroll_pos), mScrollPos);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.containsKey(getString(R.string.scroll_pos))) {
            mScrollPos = savedInstanceState.getParcelable(getString(R.string.scroll_pos));
            Objects.requireNonNull(mBinding.rvBody.getLayoutManager()).onRestoreInstanceState(mScrollPos);
        }
    }

    private void initView() {
        LiveData<List<Integer>> mIntegerIdsLiveData = vm.getBooksIdsLiveData();

        mIntegerIdsLiveData.observe(this, idList -> {
            mIntegerIdsLiveData.removeObservers(this);

            LiveData<Book> bookLiveData = vm.getBook();
            bookLiveData.observe(this, book -> {
                bookLiveData.removeObservers(this);
                vm.mapToBookViewModel(bookLiveData.getValue());
                vm.parseDataToParagraph(book.getBody());
                mBinding.setViewModel(vm);
            });

            LiveData<List<String>> bodyContentLiveData = vm.getBodyContentLiveData();
            bodyContentLiveData.observe(this, body -> {
                bodyContentLiveData.removeObservers(this);
                adapter = new FragmentArticleDetailBodyAdapter();
                mLayout = new LinearLayoutManager(this);
                mBinding.rvBody.setLayoutManager(mLayout);
                mBinding.rvBody.setAdapter(adapter);
                adapter.setData(body);
            });
        });

        mBinding.shareFab.setOnClickListener(view -> startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(this)
                .setType(getString(R.string.share_type))
                .setText(vm.mBookViewModel.getTitle().get())
                .getIntent(), getString(R.string.action_share))));

        mBinding.ibBack.setOnClickListener(view -> finishAfterTransition());
    }

    @Override
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        new Handler().postDelayed(() -> mBinding.appbar.setExpanded(false, true), 300);
    }
}