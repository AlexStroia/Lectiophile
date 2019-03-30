package com.example.xyzreader.utils;

public class StringUtils {

    public static String[] stringToParagraph(String body) {
        return body.split(Constants.PARAGRAPH_PARAMS.NEW_LINE.WINDOWS_DOS);
    }
}
