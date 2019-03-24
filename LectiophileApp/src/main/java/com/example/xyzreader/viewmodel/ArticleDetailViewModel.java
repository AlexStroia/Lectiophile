package com.example.xyzreader.viewmodel;

import android.app.Application;


import com.example.xyzreader.model.BodyViewModel;
import com.example.xyzreader.model.BookViewModel;
import com.example.xyzreader.model.ItemToVmMapper;
import com.example.xyzreader.model.StringToParagraphsMapper;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import co.alexdev.data.model.Book;
import co.alexdev.data.repo.LectiophileRepository;

public class ArticleDetailViewModel extends AndroidViewModel {

    private LiveData<Book> mBook;
    private LiveData<List<Integer>> mBooksIdsLiveData;
    private ObservableList<BodyViewModel> mBodyList;
    public BookViewModel mBookViewModel;

    public ArticleDetailViewModel(@NonNull Application application, int bookId) {
        super(application);

        mBook = LectiophileRepository.getInstance(application.getApplicationContext()).getBookById(bookId);
        mBooksIdsLiveData = LectiophileRepository.getInstance(application.getApplicationContext()).getBooksIdList();
        mBookViewModel = new BookViewModel();
        mBodyList = new ObservableArrayList<>();
    }

    public LiveData<Book> getBook() {
        return mBook;
    }

    public LiveData<List<Integer>> getBooksIdsLiveData() {
        return mBooksIdsLiveData;
    }

    /*Get the current data of Books and make them Observable*/
    public void mapToBookViewModel(Book book) {
        mBookViewModel = new ItemToVmMapper().map(book);
    }

    public void parseDataToParagraph(String body) {
        if (mBodyList.isEmpty()) {
            mBodyList = new StringToParagraphsMapper().map(body);
        }
    }
}
