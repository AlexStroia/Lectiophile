package com.example.xyzreader.database;

import com.example.xyzreader.model.Book;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface BooksDao {

    @Query("SELECT * From BOOK")
    LiveData<List<Book>> getBooks();

    @Insert
    void insert(List<Book> books);
}
