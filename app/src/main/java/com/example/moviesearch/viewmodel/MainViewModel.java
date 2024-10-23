package com.example.moviesearch.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviesearch.model.Movie;
import com.example.moviesearch.model.MovieResponse;
import com.example.moviesearch.network.ApiService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainViewModel extends ViewModel {
    private final ApiService apiService = new ApiService();
    private final MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    private final Gson gson = new Gson(); // Gson 实例

    public LiveData<List<Movie>> getMovies() {
        return moviesLiveData;
    }

    public LiveData<Boolean> getLoadingStatus() {
        return isLoading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    /**
     * 通过 API 查询电影，并解析返回的 JSON 数据。
     */
    public void searchMovies(String query) {
        isLoading.setValue(true);
        apiService.searchMovies(query, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                isLoading.postValue(false);
                errorMessage.postValue("请求失败，请检查网络连接");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    // 获取 API 返回的 JSON 数据
                    String jsonData = response.body().string();

                    // 使用 Gson 解析 JSON 数据为 MovieResponse 对象
                    MovieResponse movieResponse = gson.fromJson(jsonData, MovieResponse.class);

                    // 获取解析后的电影列表
                    List<Movie> movies = movieResponse.getSearch();

                    // 检查电影列表是否为空，并更新 UI
                    isLoading.postValue(false);
                    if (movies == null || movies.isEmpty()) {
                        errorMessage.postValue("未找到影片");
                    } else {
                        moviesLiveData.postValue(movies);
                    }
                } catch (Exception e) {
                    // 捕获解析异常并更新错误信息
                    isLoading.postValue(false);
                    errorMessage.postValue("数据解析错误");
                }
            }
        });
    }
}

