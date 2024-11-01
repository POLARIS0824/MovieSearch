package com.example.moviesearch;

import android.content.Intent;
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

import com.example.moviesearch.util.HistoryManager;
import com.example.moviesearch.viewmodel.MainViewModel;
import com.example.moviesearch.adapter.MovieAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // 变量声明
    private EditText searchInput;
    private Button searchButton;
    private ProgressBar progressBar;
    private RecyclerView moviesRecyclerView;
    private MovieAdapter movieAdapter;
    private MainViewModel viewModel;
    private HistoryManager historyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 将你在 XML 布局文件中定义的界面加载到这个 Activity 中
        searchButton = findViewById(R.id.searchButton);  // 搜索按钮
        searchInput = findViewById(R.id.searchEditText); // 搜索输入框
        progressBar = findViewById(R.id.progressBar);    // 加载动画
        moviesRecyclerView = findViewById(R.id.moviesRecyclerView);

        // 初始化 HistoryManager
        historyManager = new HistoryManager(this);

        // 配置 RecyclerView，为它设置适配器和布局管理器，使它能够正常显示列表内容
        movieAdapter = new MovieAdapter(new ArrayList<>(), historyManager); // 传递 historyManager
        moviesRecyclerView.setAdapter(movieAdapter);
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 初始化 ViewModel
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // 观察 ViewModel 中的 LiveData
        viewModel.getMovies().observe(this, movies -> movieAdapter.updateMovies(movies));
        viewModel.getLoadingStatus().observe(this, isLoading ->
                progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE));
        viewModel.getErrorMessage().observe(this, error -> {
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        });

        // 设置搜索按钮点击事件
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

        // 添加 RecyclerView 滑动监听器，加载下一页数据
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

        // 设置查看浏览记录按钮点击事件
        Button viewHistoryButton = findViewById(R.id.viewHistoryButton);
        viewHistoryButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        });
    }
}
