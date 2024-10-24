package com.example.moviesearch.model;

import android.util.Log;

import java.util.List;

public class MovieResponse {
    private List<Movie> Search;
    private static final String TAG = "MovieResponse";

    public List<Movie> getSearch() {
        for(Movie movie : Search){
            Log.d(TAG, "onResponse: "+movie.getTitle());
        }
        return Search;
    }

    public void setSearch(List<Movie> search) {
        Search = search;
    }
}


