package com.example.xyzreader.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import co.alexdev.data.model.Book;
import co.alexdev.data.repo.LectiophileRepository;

public class ArticleDetailViewModel extends AndroidViewModel {

    private LiveData<Book> mBookId;
    public ArticleDetailViewModel(@NonNull Application application, int bookId) {
        super(application);

        mBookId = LectiophileRepository.getInstance(application.getApplicationContext()).getBookById(bookId);
    }

    public LiveData<Book> getBookId() {
        return mBookId;
    }
}
