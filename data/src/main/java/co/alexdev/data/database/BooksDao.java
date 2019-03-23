package co.alexdev.data.database;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import co.alexdev.data.model.Book;

@Dao
public interface BooksDao {

    @Query("SELECT * From BOOK")
    LiveData<List<Book>> getBooks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Book> books);

    @Query("SELECT * FROM BOOK where id =:id")
    LiveData<Book> getBookById(int id);
}
