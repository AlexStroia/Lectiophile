package com.example.xyzreader.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
public class DateUtils {

    private static final String TAG = "DateUtils";
    private final static String DATE_FORMAT_UTC = "yyyy-mm-dd'T'HH:mm:ss.SSS";

    public static String formatUtcDate(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_UTC);
        Log.d(TAG, "formatUtcDate: " +  simpleDateFormat.parse(date).toString());
        return simpleDateFormat.parse(date).toString();
    }
}
