package com.example.xyzreader.model;

import android.util.Log;

import co.alexdev.data.helper.ResultMapper;
import co.alexdev.data.model.Book;

public class ItemToVmMapper implements ResultMapper<Book, BookViewModel> {
    private static final String TAG = "ItemToVmMapper";
    @Override
    public BookViewModel map(Book book) {
        BookViewModel bookViewModel = new BookViewModel();
        bookViewModel.id.set(book.getId());
        bookViewModel.author.set(book.getAuthor());
        bookViewModel.aspect_ratio.set(book.getAspect_ratio());
        bookViewModel.thumb.set(book.getThumb());
        bookViewModel.published_date.set(book.getPublished_date());
        bookViewModel.title.set(book.getTitle());
        bookViewModel.body.set(book.getBody());
        Log.d(TAG, "map: called");
        return bookViewModel;
    }
}
