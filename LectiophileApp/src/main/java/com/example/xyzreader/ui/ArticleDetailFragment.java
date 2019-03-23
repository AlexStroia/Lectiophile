package com.example.xyzreader.ui;

import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

import android.os.Bundle;

import androidx.core.app.ShareCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import co.alexdev.data.model.Book;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xyzreader.R;
import com.example.xyzreader.databinding.FragmentArticleDetailBinding;
import com.example.xyzreader.viewmodel.ArticleDetailViewModel;
import com.example.xyzreader.viewmodel.factory.ViewModelFactory;

public class ArticleDetailFragment extends Fragment {

    private static final String TAG = "ArticleDetailFragment";
    private FragmentArticleDetailBinding mBinding;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss");
    // Use default locale format
    private SimpleDateFormat outputFormat = new SimpleDateFormat();
    // Most time functions can only handle 1902 - 2037
    private GregorianCalendar START_OF_EPOCH = new GregorianCalendar(2, 1, 1);

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */

    private ArticleDetailViewModel vm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            String key = getString(R.string.book_id);
            if (getArguments().containsKey(key)) {
                int fragmentContentId = getArguments().getInt(key);
                initViewModel(fragmentContentId);
            }
        }
        setHasOptionsMenu(true);
    }

    private void initViewModel(int fragmentContentId) {
        ViewModelFactory factory = new ViewModelFactory(Objects.requireNonNull(this.getActivity()).getApplication(), fragmentContentId);
        vm = ViewModelProviders.of(this.getActivity(), factory).get(ArticleDetailViewModel.class);

        LiveData<Book> bookLiveData = vm.getBookId();

        bookLiveData.observe(this, book -> {
            bookLiveData.removeObservers(this);
            vm.mapToBookViewModel(bookLiveData.getValue());
            Log.d(TAG, "onChanged content " + book.toString());

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_article_detail, container, false);
        mBinding.setViewModel(vm);

        mBinding.shareFab.setOnClickListener(view -> startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(Objects.requireNonNull(getActivity()))
                .setType(getString(R.string.share_type))
                .setText(getString(R.string.share_data))
                .getIntent(), getString(R.string.action_share))));

        return mBinding.getRoot();
    }

    private Date parsePublishedDate() {
/*        try {
            String date = mCursor.getString(ArticleLoader.Query.PUBLISHED_DATE);
            return dateFormat.parse(date);
        } catch (ParseException ex) {
            Log.e(TAG, ex.getMessage());
            Log.i(TAG, "passing today's date");
            return new Date();
        }*/
        return null;
    }
}
