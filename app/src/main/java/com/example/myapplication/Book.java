package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private String id;
    private String name;
    private String author;
    private String category;
    private String genre;
    private boolean available;

    public Book() {
        // Default constructor required for Firebase
    }

    public Book(String id, String name, String author, String category, String genre, boolean available) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.genre = genre;
        this.available = available;
    }

    // Getter and setter methods for the fields

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getTitle() {
        return name;
    }

    // Implement the Parcelable interface methods
    protected Book(Parcel in) {
        id = in.readString();
        name = in.readString();
        author = in.readString();
        category = in.readString();
        genre = in.readString();
        available = in.readByte() != 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(author);
        dest.writeString(category);
        dest.writeString(genre);
        dest.writeByte((byte) (available ? 1 : 0));
    }
}

