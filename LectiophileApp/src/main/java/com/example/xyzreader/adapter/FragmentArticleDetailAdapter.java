package com.example.xyzreader.adapter;

import android.content.Context;
import android.os.Bundle;

import com.example.xyzreader.R;
import com.example.xyzreader.ui.ArticleDetailFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentArticleDetailAdapter extends FragmentPagerAdapter {

    private List<Integer> mBooksIdList;
    private Context mContext;

    public FragmentArticleDetailAdapter(@NonNull FragmentManager fm,
                                        @NonNull Context context) {
        super(fm);
        this.mContext = context;
        this.mBooksIdList = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        ArticleDetailFragment articleDetailFragment = new ArticleDetailFragment();
        /*Return content of the bundle*/

        int fragmentContentId = mBooksIdList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt(mContext.getString(R.string.book_id), fragmentContentId);
        articleDetailFragment.setArguments(bundle);
        return articleDetailFragment;
    }

    public void setData(List<Integer> booksIdList) {
        this.mBooksIdList = booksIdList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mBooksIdList.size() == 0) return 0;
        return mBooksIdList.size();
    }
}
