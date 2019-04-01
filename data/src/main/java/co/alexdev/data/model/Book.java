package co.alexdev.data.model;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Book {

    @PrimaryKey
    @NonNull
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("author")
    private String author;
    @SerializedName("body")
    private String body;
    @SerializedName("thumb")
    private String thumb;
    @SerializedName("aspect_ratio")
    private Double aspect_ratio;
    @SerializedName("published_date")
    private String published_date;

    public Book(int id, String title, String author, String body, String thumb, Double aspect_ratio, String published_date) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.body = body;
        this.thumb = thumb;
        this.aspect_ratio = aspect_ratio;
        this.published_date = published_date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }

    public String getThumb() {
        return thumb;
    }

    public Double getAspect_ratio() {
        return aspect_ratio;
    }

    public String getPublished_date() {
        return published_date;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", body='" + body + '\'' +
                ", thumb='" + thumb + '\'' +
                ", aspect_ratio=" + aspect_ratio +
                ", published_date='" + published_date + '\'' +
                '}';
    }
}
