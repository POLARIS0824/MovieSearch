package com.example.moviesearch.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.Range;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import com.example.moviesearch.MovieDetailActivity;
import com.example.moviesearch.R;
import com.example.moviesearch.model.Movie;
import com.example.moviesearch.model.MovieHistory;
import com.example.moviesearch.util.HistoryManager;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private final HistoryManager historyManager;

    // 修改构造方法，添加 HistoryManager 作为参数
    public MovieAdapter(List<Movie> movies, HistoryManager historyManager) {
        this.movies = movies;
        this.historyManager = historyManager;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext(); // 使用父视图的上下文
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    // 更新电影列表的方法
    public void updateMovies(List<Movie> newMovies) {
        movies = new ArrayList<>(newMovies); // 用新的电影列表替换旧的列表
        notifyDataSetChanged(); // 通知 RecyclerView 刷新界面
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, yearTextView;
        ImageView posterImageView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.movieTitleTextView);
            yearTextView = itemView.findViewById(R.id.movieYearTextView);
            posterImageView = itemView.findViewById(R.id.moviePosterImageView);
        }

        public void bind(Movie movie) {
            titleTextView.setText(movie.getTitle());
            yearTextView.setText(movie.getYear());

            // 加载海报图片
            Glide.with(itemView.getContext())
                    .load(movie.getPoster())
                    .into(posterImageView);

            // 设置点击事件，跳转到详情页，并记录浏览历史
            itemView.setOnClickListener(v -> {
                // 创建 MovieHistory 对象（仅包含电影名称和时间戳）
                MovieHistory history = new MovieHistory(
                        movie.getTitle(),
                        System.currentTimeMillis() // 当前时间戳
                );

                new Thread(
                        () -> {
                            // 添加到浏览记录
                            HistoryManager.addHistory(history);  // 使用实例调用
                            // IO操作切换线程，避免卡死
                        }
                );

                // 启动详情页 Activity
                Intent intent = new Intent(itemView.getContext(), MovieDetailActivity.class);
                intent.putExtra("movie_id", movie.getImdbID());  // 将电影的 IMDb ID 传递给详情页
                itemView.getContext().startActivity(intent);  // 启动详情页 Activity
            });
        }

    }
}
