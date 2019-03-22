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

    public ObservableInt getId() {
        return id;
    }

    public ObservableField<String> getTitle() {
        return title;
    }

    public ObservableField<String> getAuthor() {
        return author;
    }

    public ObservableField<String> getBody() {
        return body;
    }

    public ObservableField<String> getThumb() {
        return thumb;
    }

    public ObservableDouble getAspect_ratio() {
        return aspect_ratio;
    }

    public ObservableField<String> getPublished_date() {
        return published_date;
    }

    @Override
    public String toString() {
        return "BookViewModel{" +
                "id=" + id +
                ", title=" + title +
                ", author=" + author +
                ", body=" + body +
                ", thumb=" + thumb +
                ", aspect_ratio=" + aspect_ratio +
                ", published_date=" + published_date +
                '}';
    }
}
