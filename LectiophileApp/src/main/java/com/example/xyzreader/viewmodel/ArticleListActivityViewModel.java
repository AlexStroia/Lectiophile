package com.example.xyzreader.viewmodel;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import co.alexdev.data.model.Book;
import co.alexdev.data.networking.Resource;
import co.alexdev.data.repo.LectiophileRepository;

public class ArticleListActivityViewModel extends AndroidViewModel {

    private LiveData<Resource<List<Book>>> mBooksLiveData;
    private List<Book> mBooks;

    public ArticleListActivityViewModel(@NonNull Application application) {
        super(application);

        mBooksLiveData = LectiophileRepository.getInstance(this.getApplication()).getBooks();
        mBooks = new ArrayList<>();
    }

    public LiveData<Resource<List<Book>>> getBooks() {
        return mBooksLiveData;
    }

    public void setList(List<Book> books) {
        this.mBooks = books;
    }

    public List<Book> getmBookList() {
        return mBooks;
    }
}
