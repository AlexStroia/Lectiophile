package com.example.xyzreader.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;

import com.example.xyzreader.R;
import com.example.xyzreader.adapter.LectiophileAdapter;
import com.example.xyzreader.databinding.ActivityArticleListBinding;
import com.example.xyzreader.utils.listeners.OnBookSelectedListener;
import com.example.xyzreader.viewmodel.ArticleListViewModel;

public class ArticleListActivity extends AppCompatActivity implements OnBookSelectedListener {

    private static final String TAG = "ArticleListActivity";
    private ArticleListViewModel vm;
    private ActivityArticleListBinding mBinding;
    private LectiophileAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_article_list);

        vm = ViewModelProviders.of(this).get(ArticleListViewModel.class);
        mBinding.setViewModel(vm);
        mBinding.setLifecycleOwner(this);
        initView();
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);

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
            }
        });
    }

    @Override
    public void onBookItemClick(int id, ImageView imageView) {
        Intent intent = new Intent(this, ArticleDetailActivity.class);
        intent.putExtra(getString(R.string.book_click), id);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation
                (this, imageView,
                        imageView.getTransitionName());
        ActivityCompat.startActivity(this, intent, options.toBundle());
        Log.d(TAG, "onBookItemClick: " + id);
    }
}