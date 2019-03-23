package co.alexdev.data.repo;

import android.content.Context;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import co.alexdev.data.api.ApiResponse;
import co.alexdev.data.database.LectiophileDatabase;
import co.alexdev.data.model.AppExecutor;
import co.alexdev.data.model.Book;
import co.alexdev.data.networking.NetworkBoundsResource;
import co.alexdev.data.networking.Resource;
import co.alexdev.data.networking.RetrofitClient;

public class LectiophileRepository {
    private static final String TAG = "LectiophileRepository";

    private static LectiophileRepository mInstance;
    private static LectiophileDatabase mDatabase;
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
                LectiophileDatabase.getInstance(mContext).getBooksDao().insertAll(item);
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

    public LiveData<Book> getBookById(int id) {
        return LectiophileDatabase.getInstance(mContext).getBooksDao().getBookById(id);
    }

    public LiveData<List<Integer>> getBooksIdList() {
        return LectiophileDatabase.getInstance(mContext).getBooksDao().getBooksIdList();
    }

    public LiveData<List<Book>> getBooksList() {
        return LectiophileDatabase.getInstance(mContext).getBooksDao().getBooks();
    }
}
