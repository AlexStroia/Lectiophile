package com.example.xyzreader.repo;

import android.content.Context;
import android.util.Log;

import com.example.xyzreader.api.ApiResponse;
import com.example.xyzreader.database.LectiophileDatabase;
import com.example.xyzreader.model.Book;
import com.example.xyzreader.networking.NetworkBoundsResource;
import com.example.xyzreader.networking.Resource;
import com.example.xyzreader.networking.RetrofitClient;
import com.example.xyzreader.utils.AppExecutor;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class LectiophileRepository {
    private static final String TAG = "LectiophileRepository";

    private static LectiophileRepository mInstance;
    private Context mContext;

    private LectiophileRepository(Context mContext) {
        this.mContext = mContext;
    }

    public static LectiophileRepository getInstance(Context context) {
        if (mInstance == null) {
            return new LectiophileRepository(context);
        }
        return mInstance;
    }

    public LiveData<Resource<List<Book>>> getBooks() {
        return new NetworkBoundsResource<List<Book>, List<Book>>(AppExecutor.getInstance()) {


            @Override
            protected void saveCallResult(@NonNull List<Book> item) {
                Log.d(TAG, "saveCallResult: " + item);
                Log.d(TAG, "saveCallResult: " + item.size());
            }

            @Override
            protected boolean shouldFetch(@NonNull List<Book> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Book>> loadFromDatabase() {
                return LectiophileDatabase.getInstance(mContext).getBooksDao().getBooks();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Book>>> createCall() {
                return RetrofitClient.getInstance().getLectiophileService().getBooks();
            }
        }.asLiveData();
    }
}
