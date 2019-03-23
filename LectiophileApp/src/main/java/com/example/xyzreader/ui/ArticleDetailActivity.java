package com.example.xyzreader.ui;

import android.os.Bundle;
import android.util.Log;

import com.example.xyzreader.R;
import com.example.xyzreader.adapter.FragmentArticleDetailAdapter;
import com.example.xyzreader.databinding.ActivityArticleDetailBinding;
import com.example.xyzreader.viewmodel.ArticleDetailViewModel;
import com.example.xyzreader.viewmodel.factory.ViewModelFactory;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

/**
 * An activity representing a single Article detail screen, letting you swipe between articles.
 */
public class ArticleDetailActivity extends AppCompatActivity {

    private static final String TAG = "ArticleDetailActivity";

    private ArticleDetailViewModel vm;
    private ActivityArticleDetailBinding mBinding;

    private int mBookId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_article_detail);
        if (getIntent() != null) {
            mBookId = getIntent().getIntExtra(getString(R.string.book_click), 0);
            initView(mBookId);
            Log.d(TAG, "onCreate: " + mBookId);
        }
    }

    private void initView(int id) {
        ViewModelFactory factory = new ViewModelFactory(this.getApplication(), id);
        vm = ViewModelProviders.of(this, factory).get(ArticleDetailViewModel.class);
        vm.getBookId().observe(this, book -> {
            Log.d(TAG, "initView: " + book.toString());
        });

        LiveData<List<Integer>> mIntegerIdsLiveData = vm.getBooksIdsLiveData();
        mIntegerIdsLiveData.observe(this, idList -> {
            Log.d(TAG, "initView size: " + idList.size());
            mIntegerIdsLiveData.removeObservers(this);

            ArticleDetailFragment fragment = new ArticleDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(getString(R.string.book_id), id);
            fragment.setArguments(bundle);
            changeFragment(fragment);

            //     FragmentArticleDetailAdapter adapter = new FragmentArticleDetailAdapter(getSupportFragmentManager(), this);
            //     adapter.setData(idList);
            //      mBinding.pager.setAdapter(adapter);
        });
    }

    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commitAllowingStateLoss();
    }
}
