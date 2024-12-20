package com.example.moviesearch.model;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName(value = "Title")
    private String title;
    @SerializedName(value = "Year")
    private String year;
    @SerializedName(value = "Poster")
    private String poster;
    private String imdbID;
    /* 封装的原则：通过 getter 和 setter 方法来访问和修改这些
    私有变量，从而实现数据的保护，防止外部代码直接修改它们 */

    public Movie(String title, String year, String poster, String imdbID) {
        this.title = title;
        this.year = year;
        this.poster = poster;
        this.imdbID = imdbID;
    }
    //this用于引用当前对象的实例，区分参数和成员变量

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

    public String getImdbID() {
        return imdbID;
    }
}
