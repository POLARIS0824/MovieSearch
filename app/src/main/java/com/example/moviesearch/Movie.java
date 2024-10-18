package com.example.moviesearch;

public class Movie {
    private String title;
    private String year;
    private String poster;

    public Movie(String title, String year, String poster) {
        this.title = title;
        this.year = year;
        this.poster = poster;
    }

    //Getter方法，用于获取属性值
    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getPoster() {
        return poster;
    }
}
