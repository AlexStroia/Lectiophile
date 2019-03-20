package com.example.xyzreader.networking;

import com.example.xyzreader.utils.Constants;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {

    @NonNull
    @Constants.NETWORKING_STATUS.NetworkStatus
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
        return new Resource<>(Constants.NETWORKING_STATUS.RESPONSE_SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String message, @Nullable T data) {
        return new Resource<>(Constants.NETWORKING_STATUS.RESPONSE_ERROR, data, message);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(Constants.NETWORKING_STATUS.RESPONSE_LOADING, data, null);
    }
}
