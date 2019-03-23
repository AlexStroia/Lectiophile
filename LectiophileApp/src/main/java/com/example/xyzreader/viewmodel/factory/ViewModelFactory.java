package com.example.xyzreader.viewmodel.factory;

import android.app.Application;

import com.example.xyzreader.viewmodel.ArticleDetailViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private int bookId;
    private Application application;

    public ViewModelFactory(Application application, int bookId) {
        this.application = application;
        this.bookId = bookId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ArticleDetailViewModel(application, bookId);
    }
}
