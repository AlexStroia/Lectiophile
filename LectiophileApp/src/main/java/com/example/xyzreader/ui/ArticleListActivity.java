package com.example.xyzreader.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.xyzreader.R;
import com.example.xyzreader.adapter.LectiophileAdapter;
import com.example.xyzreader.databinding.ActivityArticleListBinding;
import com.example.xyzreader.model.BookViewModel;
import com.example.xyzreader.utils.listeners.OnBookSelectedListener;
import com.example.xyzreader.viewmodel.ArticleListActivityViewModel;

public class ArticleListActivity extends AppCompatActivity implements OnBookSelectedListener {

    private static final String TAG = "ArticleListActivity";
    private ArticleListActivityViewModel vm;
    private ActivityArticleListBinding mBinding;
    private LectiophileAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_article_list);

        vm = ViewModelProviders.of(this).get(ArticleListActivityViewModel.class);
        getLifecycle().addObserver(vm);
        mBinding.setViewModel(vm);
        initView();
    }

    private void initView() {
        mAdapter = new LectiophileAdapter();
        mAdapter.setListener(this);
        fetchData();
        initSwipeRefreshLayout();
    }

    private void initSwipeRefreshLayout() {
        mBinding.swipeRefreshLayout.setOnRefreshListener(() -> vm.setIsRefreshing(mBinding.swipeRefreshLayout.isRefreshing()));

        vm.getIsRefreshing().observe(this, isRefreshing -> {
            if (isRefreshing) mBinding.swipeRefreshLayout.setRefreshing(false);
            if (mAdapter != null) {
                mAdapter.setData(vm.getBooksObservable());
            }
        });
    }

    private void fetchData() {
        vm.getBooks().observe(this, listResourceBooks -> {
            if (listResourceBooks.data != null && listResourceBooks.data.size() > 0) {
                vm.mapToBookViewModel(listResourceBooks.data);
                Log.d(TAG, "fetchData: " + vm.getBooksObservable().size());
                //   setRecyclerView(vm.getmBookList());
            }
        });
    }

    @Override
    public void onBookItemClick(BookViewModel bookViewModel) {

        Log.d(TAG, "onBookItemClick: " + bookViewModel);
    }
}