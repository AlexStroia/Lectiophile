package com.example.xyzreader.model;

import androidx.databinding.ObservableDouble;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

public class BookViewModel {

    ObservableInt id;
    ObservableField<String> title;
    ObservableField<String> author;
    ObservableField<String> body;
    ObservableField<String> thumb;
    ObservableDouble aspect_ratio;
    ObservableField<String> published_date;

    public BookViewModel() {
        this.id = new ObservableInt();
        this.title = new ObservableField<>();
        this.author = new ObservableField<>();
        this.body = new ObservableField<>();
        this.thumb = new ObservableField<>();
        this.aspect_ratio = new ObservableDouble();
        this.published_date = new ObservableField<>();
    }
}
