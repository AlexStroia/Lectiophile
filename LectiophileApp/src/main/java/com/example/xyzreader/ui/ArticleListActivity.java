package com.example.xyzreader.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import co.alexdev.data.model.Book;

import com.example.xyzreader.R;
import com.example.xyzreader.adapter.LectiophileAdapter;
import com.example.xyzreader.databinding.ActivityArticleListBinding;
import com.example.xyzreader.utils.DividerDecoration;
import com.example.xyzreader.viewmodel.ArticleListActivityViewModel;

import java.util.List;

/**
 * An activity representing a list of Articles. This activity has different presentations for
 * handset and tablet-size devices. On handsets, the activity presents a list of items, which when
 * touched, lead to a {@link ArticleDetailActivity} representing item details. On tablets, the
 * activity presents a grid of items as cards.
 */
public class ArticleListActivity extends AppCompatActivity {

    private static final String TAG = "ArticleListActivity";
    private ArticleListActivityViewModel vm;
    private ActivityArticleListBinding mBinding;
    LectiophileAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_article_list);

        vm = ViewModelProviders.of(this).get(ArticleListActivityViewModel.class);
        initView();
    }

    private void initView() {
        fetchData();

        mBinding.swipeRefreshLayout.setOnRefreshListener(() -> {
            if (mBinding.swipeRefreshLayout.isRefreshing()) {
                mBinding.swipeRefreshLayout.setRefreshing(false);
                mAdapter.setData(vm.getmBookList());
            }
        });
    }

    private void setRecyclerView(List<Book> books) {
        mAdapter = new LectiophileAdapter();
        mBinding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.recyclerView.addItemDecoration(new DividerDecoration(8));
        mAdapter.setData(books);
    }

    private void fetchData() {
        vm.getBooks().observe(this, listResourceBooks -> {
            if (listResourceBooks.data != null && listResourceBooks.data.size() > 0) {
                vm.setList(listResourceBooks.data);
                setRecyclerView(vm.getmBookList());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
