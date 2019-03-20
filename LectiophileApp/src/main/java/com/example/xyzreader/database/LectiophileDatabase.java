package com.example.xyzreader.database;

import android.content.Context;

import com.example.xyzreader.model.Book;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Book.class}, version = 1, exportSchema = false)
public abstract class LectiophileDatabase extends RoomDatabase {


    private static LectiophileDatabase sInstance;
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "LECTIOPHILE_DATABASE";

    public static LectiophileDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(), LectiophileDatabase.class, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();

            }
        }
        return sInstance;
    }

    public abstract BooksDao getBooksDao();
}
