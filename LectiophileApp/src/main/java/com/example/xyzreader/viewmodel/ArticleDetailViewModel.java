package com.example.xyzreader.viewmodel;

import android.app.Application;
import com.example.xyzreader.model.BookViewModel;
import com.example.xyzreader.model.ItemToVmMapper;
import com.example.xyzreader.utils.Constants;

import java.util.Arrays;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import co.alexdev.data.model.Book;
import co.alexdev.data.repo.LectiophileRepository;

public class ArticleDetailViewModel extends AndroidViewModel {

    private LiveData<Book> mBook;
    private LiveData<List<Integer>> mBooksIdsLiveData;
    private MutableLiveData<List<String>> mBodyContentLiveData = new MutableLiveData<>();
    public BookViewModel mBookViewModel;
    private static final String TAG = "ArticleDetailViewModel";

    public ArticleDetailViewModel(@NonNull Application application, int bookId) {
        super(application);

        mBook = LectiophileRepository.getInstance(application.getApplicationContext()).getBookById(bookId);
        mBooksIdsLiveData = LectiophileRepository.getInstance(application.getApplicationContext()).getBooksIdList();
        mBookViewModel = new BookViewModel();
       // mBodyList = new ObservableArrayList<>();
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
     //new StringToParagraphsMapper().map(body);
            String[] paragraphs = body.split(Constants.PARAGRAPH_PARAMS.NEW_LINE.WINDOWS_DOS);
            mBodyContentLiveData.setValue(Arrays.asList(paragraphs));

    //    }
    }

    public MutableLiveData<List<String>> getBodyContentLiveData() {
        return mBodyContentLiveData;
    }
}
