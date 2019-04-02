package com.example.xyzreader.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.alexdev.data.helper.ResultMapper;
import co.alexdev.data.model.Book;

public class ItemsToVmMapper implements ResultMapper<List<Book>, List<BookViewModel>> {

    private static final String TAG = "ItemsToVmMapper";

    @Override
    public List<BookViewModel> map(List<co.alexdev.data.model.Book> books) {
        List<BookViewModel> bookViewModelList = new ArrayList<>();
        for (co.alexdev.data.model.Book book : books) {
            BookViewModel bookViewModel = new BookViewModel();
            bookViewModel.id.set(book.getId());
            bookViewModel.author.set(book.getAuthor());
            bookViewModel.aspect_ratio.set(book.getAspect_ratio() == null ? 0 : book.getAspect_ratio());
            bookViewModel.thumb.set(book.getThumb());
            bookViewModel.published_date.set(book.getPublished_date());
            bookViewModel.title.set(book.getTitle());
            bookViewModel.body.set(book.getBody());
            bookViewModelList.add(bookViewModel);

            Log.d(TAG, "map: " + bookViewModel.toString());
        }
        return bookViewModelList;
    }
}
