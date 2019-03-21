package co.alexdev.data.networking;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {

    @NonNull
    @NETWORKING_STATUS.NetworkStatus
    public final int status;

    @Nullable
    public final String message;

    @Nullable
    public final T data;

    public Resource(@NonNull int status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(NETWORKING_STATUS.RESPONSE_SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String message, @Nullable T data) {
        return new Resource<>(NETWORKING_STATUS.RESPONSE_ERROR, data, message);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(NETWORKING_STATUS.RESPONSE_LOADING, data, null);
    }


    private static class NETWORKING_STATUS {

        static final int RESPONSE_ERROR = 0;
        static final int RESPONSE_SUCCESS = 1;
        static final int RESPONSE_LOADING = 2;

        @Retention(RetentionPolicy.SOURCE)
        @IntDef({RESPONSE_ERROR, RESPONSE_SUCCESS, RESPONSE_LOADING})
        @interface NetworkStatus {
        }
    }
}
