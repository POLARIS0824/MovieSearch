package com.example.moviesearch.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import com.example.moviesearch.R;
import com.example.moviesearch.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.title.setText(movie.getTitle());
        holder.year.setText(movie.getYear());
        Glide.with(holder.itemView.getContext()).load(movie.getPoster()).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void updateMovies(List<Movie> newMovies) {
        movies = new ArrayList<>(newMovies); // 用新的电影列表替换旧的列表
        notifyDataSetChanged(); // 通知 RecyclerView 刷新界面
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title, year;
        ImageView poster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movieTitleTextView);
            year = itemView.findViewById(R.id.movieYearTextView);
            poster = itemView.findViewById(R.id.moviePosterImageView);
        }
    }
}
