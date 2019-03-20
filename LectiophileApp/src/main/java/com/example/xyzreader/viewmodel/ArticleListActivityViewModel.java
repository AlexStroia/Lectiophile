package com.example.xyzreader.viewmodel;

import android.app.Application;

import com.example.xyzreader.model.Book;
import com.example.xyzreader.networking.Resource;
import com.example.xyzreader.repo.LectiophileRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ArticleListActivityViewModel extends AndroidViewModel {

    private LiveData<Resource<List<Book>>> mBooks;

    public ArticleListActivityViewModel(@NonNull Application application) {
        super(application);

        mBooks = LectiophileRepository.getInstance(application.getApplicationContext()).getBooks();
    }

    public LiveData<Resource<List<Book>>> getBooks() {
        return mBooks;
    }
}
