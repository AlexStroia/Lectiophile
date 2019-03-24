package com.example.xyzreader.model;

import com.example.xyzreader.utils.Constants;

import java.util.Arrays;
import java.util.List;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import co.alexdev.data.helper.ResultMapper;

public class StringToParagraphsMapper implements ResultMapper<String, ObservableList<BodyViewModel>> {

    @Override
    public ObservableList<BodyViewModel> map(String s) {
        String[] paragraphs = s.split(Constants.PARAGRAPH_PARAMS.NEW_LINE.WINDOWS_DOS);
        List<String> paragraphList = Arrays.asList(paragraphs);
        BodyViewModel bodyViewModel = new BodyViewModel();

        ObservableList<BodyViewModel> observableParagraphs = new ObservableArrayList<>();

        for (String par : paragraphList) {
            bodyViewModel.setParagraph(par);
            observableParagraphs.add(bodyViewModel);
        }

        return observableParagraphs;
    }
}
