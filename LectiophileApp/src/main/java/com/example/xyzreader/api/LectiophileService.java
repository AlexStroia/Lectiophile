package com.example.xyzreader.api;

import com.example.xyzreader.model.Book;

import java.util.List;

import androidx.lifecycle.LiveData;
import retrofit2.http.GET;

public interface LectiophileService {

    @GET("topher/2017/March/58c5d68f_xyz-reader/xyz-reader.json/")
    LiveData<ApiResponse<List<Book>>> getBooks();
}
