package com.example.moviesearch;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

public class MovieItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_item);

        // 获取传递过来的数据
        String title = getIntent().getStringExtra("title");
        String year = getIntent().getStringExtra("year");
        String posterUrl = getIntent().getStringExtra("poster");

        // 绑定 UI 组件
        TextView titleTextView = findViewById(R.id.movieTitleTextView);
        TextView yearTextView = findViewById(R.id.movieYearTextView);
        ImageView posterImageView = findViewById(R.id.moviePosterImageView);

        // 设置数据
        titleTextView.setText(title);
        yearTextView.setText(year);
        Picasso.get().load(posterUrl).into(posterImageView);
    }
}

