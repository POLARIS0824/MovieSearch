package com.example.moviesearch.network;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Callback;

public class ApiService {
    private static final String API_KEY = "b862c432";
    private final OkHttpClient client = new OkHttpClient();

    public void searchMovies(String query, Callback callback) {
        String url = "https://www.omdbapi.com/?apikey=" + API_KEY + "&s=" + query;
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }
}

