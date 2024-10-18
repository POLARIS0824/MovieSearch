package com.example.moviesearch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

// 1. 创建适配器类，继承 RecyclerView.Adapter，并定义 ViewHolder
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final List<Movie> movies;  // 存储电影数据的列表

    // 2. 构造函数：初始化电影列表数据
    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    // 3. 创建 ViewHolder：将布局文件转换为 ViewHolder 对象
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);  // 绑定单个列表项布局
        return new MovieViewHolder(view);
    }

    // 4. 绑定数据到 ViewHolder：将每个电影对象的数据绑定到列表项上
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);  // 获取当前电影对象

        // 设置标题和年份
        holder.title.setText(movie.getTitle());
        holder.year.setText(movie.getYear());

        // 使用 Picasso 加载海报图片
        Picasso.get().load(movie.getPoster()).into(holder.poster);
    }

    // 5. 返回列表项的数量
    @Override
    public int getItemCount() {
        return movies.size();
    }

    // 6. 定义 ViewHolder 类：绑定 UI 组件
    static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title, year;
        ImageView poster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            // 绑定 UI 组件
            title = itemView.findViewById(R.id.movieTitleTextView);
            year = itemView.findViewById(R.id.movieYearTextView);
            poster = itemView.findViewById(R.id.moviePosterImageView);
        }
    }
}

