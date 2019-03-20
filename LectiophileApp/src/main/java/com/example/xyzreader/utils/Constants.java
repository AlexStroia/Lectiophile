package com.example.xyzreader.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

public class Constants {
    public static final int CIRCULAR_PROGRESS_TIME = 5000;
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.sss";
    public static final int DISTANCE_DECORATION = 24;

    public static class NETWORKING_STATUS {

        public static final int RESPONSE_ERROR = 0;
        public static final int RESPONSE_SUCCESS = 1;
        public static final int RESPONSE_LOADING = 2;

        @Retention(RetentionPolicy.SOURCE)
        @IntDef({RESPONSE_ERROR, RESPONSE_SUCCESS, RESPONSE_LOADING})
        public @interface NetworkStatus {
        }
    }

}
