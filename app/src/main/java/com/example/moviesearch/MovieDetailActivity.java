package com.example.moviesearch;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.moviesearch.model.MovieDetail;
import com.example.moviesearch.viewmodel.MovieDetailViewModel;

public class MovieDetailActivity extends AppCompatActivity {
    // 声明 UI 组件和 ViewModel
    private TextView titleTextView, yearTextView, genreTextView, plotTextView, directorTextView;
    private ImageView posterImageView;
    private MovieDetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        // 初始化 UI 组件
        titleTextView = findViewById(R.id.titleTextView);
        yearTextView = findViewById(R.id.yearTextView);
        genreTextView = findViewById(R.id.genreTextView);
        plotTextView = findViewById(R.id.plotTextView);
        directorTextView = findViewById(R.id.directorTextView);
        posterImageView = findViewById(R.id.posterImageView);

        // 获取从 MainActivity 传递过来的电影 ID
        String movieId = getIntent().getStringExtra("movie_id");
        if (movieId == null) {
            Toast.makeText(this, "无法获取电影详情", Toast.LENGTH_SHORT).show();
            finish(); // 如果没有传递 ID，关闭当前 Activity
            return;
        }

        // 初始化 ViewModel
        viewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);

        // 观察 LiveData，更新 UI
        viewModel.getMovieDetail().observe(this, movieDetail -> {
            if (movieDetail != null) {
                updateUI(movieDetail); // 更新 UI
            } else {
                Toast.makeText(this, "未找到影片详情", Toast.LENGTH_SHORT).show();
            }
        });

        // 请求电影详情数据
        viewModel.fetchMovieDetails(movieId);
    }

    // 更新 UI 的方法
    private void updateUI(MovieDetail movieDetail) {
        titleTextView.setText(movieDetail.getTitle());
        yearTextView.setText(movieDetail.getYear());
        genreTextView.setText(movieDetail.getGenre());
        plotTextView.setText(movieDetail.getPlot());
        directorTextView.setText("导演: " + movieDetail.getDirector());

        // 使用 Glide 加载电影海报
        Glide.with(this)
                .load(movieDetail.getPoster())
                .into(posterImageView);
    }
}

