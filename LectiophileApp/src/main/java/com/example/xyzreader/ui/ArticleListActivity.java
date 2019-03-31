package com.example.xyzreader.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.xyzreader.R;
import com.example.xyzreader.adapter.LectiophileAdapter;
import com.example.xyzreader.databinding.ActivityArticleListBinding;
import com.example.xyzreader.utils.TransitionUtils;
import com.example.xyzreader.utils.listeners.OnBookSelectedListener;
import com.example.xyzreader.viewmodel.ArticleListViewModel;

import java.util.Objects;

public class ArticleListActivity extends AppCompatActivity implements OnBookSelectedListener {

    private ArticleListViewModel vm;
    private ActivityArticleListBinding mBinding;
    private LectiophileAdapter mAdapter;
    private StaggeredGridLayoutManager mLayout;
    private Parcelable mScrollPos;

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
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

         mLayout = (StaggeredGridLayoutManager) mBinding.recyclerView.getLayoutManager();
         if(mLayout != null) {
             mScrollPos = mLayout.onSaveInstanceState();
             outState.putParcelable(getString(R.string.scroll_pos), mScrollPos);
         }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null) {
            mScrollPos = savedInstanceState.getParcelable(getString(R.string.scroll_pos));
            Objects.requireNonNull(mBinding.recyclerView.getLayoutManager()).onRestoreInstanceState(mScrollPos);
        }
    }



    private void initView() {
        TransitionUtils.setTransition(this);
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
    }
}