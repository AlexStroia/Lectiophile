package com.example.xyzreader.model;

import android.util.Log;

import com.example.xyzreader.utils.Constants;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import co.alexdev.data.helper.ResultMapper;

public class StringToParagraphsMapper implements ResultMapper<String, ObservableList<BodyViewModel>> {

    private static final String TAG = "StringToParagraphsMappe";

    @Override
    public ObservableList<BodyViewModel> map(String s) {
        String[] paragraphs = s.split(Constants.PARAGRAPH_PARAMS.NEW_LINE.WINDOWS_DOS);
        BodyViewModel bodyViewModel = new BodyViewModel();

        ObservableList<BodyViewModel> observableParagraphs = new ObservableArrayList<>();
        Log.d(TAG, "map: " + paragraphs.length);
        for (String par : paragraphs) {
            Log.d(TAG, "map: " + par);
            bodyViewModel.setParagraph(par);
            observableParagraphs.add(bodyViewModel);
        }
        Log.d(TAG, "map size is: " + observableParagraphs.size());
        return observableParagraphs;
    }
}
