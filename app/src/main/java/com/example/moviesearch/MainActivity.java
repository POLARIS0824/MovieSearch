package com.example.moviesearch;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.moviesearch.viewmodel.MainViewModel;
import com.example.moviesearch.adapter.MovieAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //变量声明
    private EditText searchInput;
    private Button searchButton;
    private ProgressBar progressBar;
    private RecyclerView moviesRecyclerView;
    private MovieAdapter movieAdapter;
    private MainViewModel viewModel;
    /*
    在 Android 中，Adapter（适配器） 是用来连接 数据源 和 用户界面（UI）组件 的桥梁
    对于 RecyclerView（用来显示电影列表），我们需要一个适配器类，即 MovieAdapter
    */

    /* private ArrayList<Movie> movies = new ArrayList<>();
    private final String API_KEY = "b862c432"; */

    @Override
    //这是每个 Activity 的生命周期方法。它在应用启动或重新创建时被调用
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //将你在 XML 布局文件中定义的界面加载到这个 Activity 中
        searchButton = findViewById(R.id.searchButton);  //输入框
        searchInput = findViewById(R.id.searchEditText); //按钮
        progressBar = findViewById(R.id.progressBar);    //加载动画
        moviesRecyclerView = findViewById(R.id.moviesRecyclerView);

        //配置 RecyclerView，为它设置适配器和布局管理器，使它能够正常显示列表内容
        movieAdapter = new MovieAdapter(this, new ArrayList<>());
        moviesRecyclerView.setAdapter(movieAdapter);
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置按钮点击事件,为搜索按钮设置一个点击事件监听器，当用户点击按钮时，会调用 searchMovies() 方法
        //searchButton.setOnClickListener(v -> searchMovies());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.getMovies().observe(this, movies -> movieAdapter.updateMovies(movies));
        viewModel.getLoadingStatus().observe(this, isLoading ->
                progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE));
        viewModel.getErrorMessage().observe(this, error -> {
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        });

        searchButton.setOnClickListener(v -> {
            String query = searchInput.getText().toString().trim();
            if (!query.isEmpty()) {
                // 改进：在用户进行新的搜索时，需要确保清空旧数据，以防止 UI 显示混乱
                movieAdapter.updateMovies(new ArrayList<>());  // 清空旧数据
                viewModel.searchMovies(query);
            } else {
                Toast.makeText(this, "请输入影片名称", Toast.LENGTH_SHORT).show();
            }
        });

        moviesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // 获取 RecyclerView 的 LayoutManager
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 检测用户是否滑动到底部
                if (layoutManager != null &&
                        // 获取当前列表中最后一个完全可见的项目的位置 && 获取当前 RecyclerView 中总共的项目数
                        layoutManager.findLastCompletelyVisibleItemPosition() == movieAdapter.getItemCount() - 1) {
                    // 滑动到底部时，加载下一页数据
                    // 获取用户在搜索框中输入的查询关键词，并去除前后空格
                    String query = searchInput.getText().toString().trim();
                    // 检查用户是否输入了有效的关键词。如果搜索框为空，则不执行下一页加载
                    if (!query.isEmpty()) {
                        viewModel.loadNextPage(query);  // 加载下一页
                    }
                }
            }
        });
    }
}