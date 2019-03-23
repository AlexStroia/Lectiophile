package com.example.xyzreader.viewmodel;

import android.app.Application;

import com.example.xyzreader.model.BookViewModel;
import com.example.xyzreader.model.ItemsToVmMapper;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import co.alexdev.data.model.Book;
import co.alexdev.data.networking.Resource;
import co.alexdev.data.repo.LectiophileRepository;

public class ArticleListViewModel extends AndroidViewModel implements LifecycleObserver {

    private LiveData<Resource<List<Book>>> mBooksLiveData;
    private MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>();
    private ObservableList<BookViewModel> mBooksObservable;
    private BookViewModel mBookViewModel;

    public ArticleListViewModel(@NonNull Application application) {
        super(application);

        isRefreshing.setValue(false);
        mBooksLiveData = LectiophileRepository.getInstance(this.getApplication()).getBooks();
        mBooksObservable = new ObservableArrayList<>();
        mBookViewModel = new BookViewModel();
    }

    /*Get the current data of Books and make them Observable*/
    public void mapToBookViewModel(List<Book> books) {
        if (mBooksObservable.isEmpty()) {
            mBooksObservable.addAll(new ItemsToVmMapper().map(books));
        }
    }

    public LiveData<Resource<List<Book>>> getBooks() {
        return mBooksLiveData;
    }

    public ObservableList<BookViewModel> getBooksObservable() {
        return mBooksObservable;
    }

    public void setIsRefreshing(Boolean isRefreshing) {
        this.isRefreshing.setValue(isRefreshing);
    }

    public MutableLiveData<Boolean> getIsRefreshing() {
        return isRefreshing;
    }
}
