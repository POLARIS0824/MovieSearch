package com.example.moviesearch.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.moviesearch.model.MovieDetail;
import com.example.moviesearch.network.ApiService;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MovieDetailViewModel extends ViewModel {
    private final ApiService apiService = new ApiService();
    private final MutableLiveData<MovieDetail> movieDetailLiveData = new MutableLiveData<>();
    private final Gson gson = new Gson();

    public LiveData<MovieDetail> getMovieDetail() {
        return movieDetailLiveData;
    }

    public void fetchMovieDetails(String movieId) {
        apiService.getMovieDetails(movieId, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 处理请求失败
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                MovieDetail movieDetail = gson.fromJson(jsonData, MovieDetail.class);
                movieDetailLiveData.postValue(movieDetail);
            }
        });
    }
}

