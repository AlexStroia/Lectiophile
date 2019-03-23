package com.example.xyzreader.viewmodel;

import android.app.Application;


import com.example.xyzreader.model.BookViewModel;
import com.example.xyzreader.model.ItemToVmMapper;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import co.alexdev.data.model.Book;
import co.alexdev.data.repo.LectiophileRepository;

public class ArticleDetailViewModel extends AndroidViewModel {

    private LiveData<Book> mBookIdLiveData;
    private LiveData<List<Integer>> mBooksIdsLiveData;
    private LiveData<List<Book>> mBooksListLiveData;
    public BookViewModel mBookViewModel;

    public ArticleDetailViewModel(@NonNull Application application, int bookId) {
        super(application);

        mBookIdLiveData = LectiophileRepository.getInstance(application.getApplicationContext()).getBookById(bookId);
        mBooksIdsLiveData = LectiophileRepository.getInstance(application.getApplicationContext()).getBooksIdList();
        mBooksListLiveData = LectiophileRepository.getInstance(application.getApplicationContext()).getBooksList();
        mBookViewModel = new BookViewModel();
    }

    public LiveData<Book> getBookId() {
        return mBookIdLiveData;
    }

    public LiveData<List<Integer>> getBooksIdsLiveData() {
        return mBooksIdsLiveData;
    }

    /*Get the current data of Books and make them Observable*/
    public void mapToBookViewModel(Book book) {
        mBookViewModel = new ItemToVmMapper().map(book);
    }
}
