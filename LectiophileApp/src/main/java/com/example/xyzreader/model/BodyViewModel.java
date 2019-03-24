package com.example.xyzreader.model;

import androidx.databinding.ObservableField;

public class BodyViewModel {

    private ObservableField<String> paragraph;

    public BodyViewModel() {
        this.paragraph = new ObservableField<>();
    }

    public void setParagraph(String paragraph) {
        this.paragraph.set(paragraph);
    }

    public ObservableField<String> getParagraph() {
        return paragraph;
    }
}
