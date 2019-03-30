package com.example.xyzreader.ui;

import android.content.Intent;

import java.util.Objects;

import android.os.Bundle;

import androidx.core.app.ShareCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import co.alexdev.data.model.Book;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xyzreader.R;
import com.example.xyzreader.adapter.FragmentArticleDetailBodyAdapter;
import com.example.xyzreader.databinding.FragmentArticleDetailBinding;
import com.example.xyzreader.viewmodel.ArticleDetailViewModel;
import com.example.xyzreader.viewmodel.factory.ViewModelFactory;

public class ArticleDetailFragment extends Fragment {

    private FragmentArticleDetailBinding mBinding;
    private ArticleDetailViewModel vm;
    private FragmentArticleDetailBodyAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();

        if (getArguments() != null) {
            String key = getString(R.string.book_id);
            if (getArguments().containsKey(key)) {
                int fragmentContentId = getArguments().getInt(key);
                initViewModel(fragmentContentId);
            }
        }
        setHasOptionsMenu(true);
    }

    private void initViewModel(final int fragmentContentId) {
        ViewModelFactory factory = new ViewModelFactory(Objects.requireNonNull(this.getActivity()).getApplication(), fragmentContentId);
        vm = ViewModelProviders.of(this.getActivity(), factory).get(ArticleDetailViewModel.class);

        LiveData<Book> bookLiveData = vm.getBook();
        bookLiveData.observe(this, book -> {
            bookLiveData.removeObservers(this);
            vm.mapToBookViewModel(bookLiveData.getValue());
            vm.parseDataToParagraph(book.getBody());
        });

        vm.getBodyContentLiveData().observe(this, body -> {
                    adapter = new FragmentArticleDetailBodyAdapter();
                    mBinding.rvBody.setLayoutManager(new LinearLayoutManager(this.getActivity()));
                    mBinding.rvBody.setAdapter(adapter);
                    adapter.setData(body);
                }
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_article_detail, container, false);
        mBinding.setViewModel(vm);
        mBinding.setLifecycleOwner(this);
     //   ViewCompat.setTransitionName(mBinding.photo, getString(R.string.TRANSITION_PHOTO));

        mBinding.shareFab.setOnClickListener(view -> startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(Objects.requireNonNull(getActivity()))
                .setType(getString(R.string.share_type))
                .setText(getString(R.string.share_data))
                .getIntent(), getString(R.string.action_share))));

        mBinding.ibBack.setOnClickListener(view -> {
            if (getActivity() != null) {
                getActivity().finishAfterTransition();
                ArticleDetailFragment.this.getActivity().finish();
            }
        });

        return mBinding.getRoot();
    }
}
