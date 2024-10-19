package com.example.moviesearch;

import android.os.Bundle;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Call;
import okhttp3.Callback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.moviesearch.Movie;

public class MainActivity extends AppCompatActivity {
    //变量声明
    private EditText searchInput;
    private Button searchButton;
    private ProgressBar progressBar;
    private RecyclerView moviesRecyclerView;
    private MovieAdapter movieAdapter;
    /*
    在 Android 中，Adapter（适配器） 是用来连接 数据源 和 用户界面（UI）组件 的桥梁
    对于 RecyclerView（用来显示电影列表），我们需要一个适配器类，即 MovieAdapter
    */

    private List<Movie> movies = new ArrayList<>();
    private final String API_KEY = "b862c432";

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
        movieAdapter = new MovieAdapter(movies);
        moviesRecyclerView.setAdapter(movieAdapter);
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置按钮点击事件,为搜索按钮设置一个点击事件监听器，当用户点击按钮时，会调用 searchMovies() 方法
        searchButton.setOnClickListener(v -> searchMovies());
    }

    /*
    从输入框获取用户输入的电影名称
    使用 OkHttp 发起网络请求，调用 OMDb API 获取电影数据
    解析 API 返回的 JSON 数据
    更新 UI：在 RecyclerView 中显示电影列表
     */
    private void searchMovies() {
        //获取用户输入并检查是否为空
        String query = searchInput.getText().toString().trim();
        //getText()：从 EditText 获取用户输入的文本
        //trim()：去除输入内容前后空格，避免不必要的错误
        if (query.isEmpty()) {   //isEmpty()：检查输入是否为空。如果为空，提示用户输入
            Toast.makeText(this, "请输入影片名称", Toast.LENGTH_SHORT).show();
            return;
        }
            /*
            toast是一种轻量级消息提示框，用于短时间提示用户信息
            makeText()：这是一个静态方法，用于创建 Toast 对象
            this：指向当前的 Context（通常是 Activity）
            "请输入影片名称"：要显示的文本消息
            Toast.LENGTH_SHORT：显示的持续时间，可以是：
            Toast.LENGTH_SHORT：短时间显示（约 2 秒）
            Toast.LENGTH_LONG：长时间显示（约 3.5 秒）
            show()：显示 Toast
             */

        progressBar.setVisibility(View.VISIBLE);
        //将加载动画的 ProgressBar 设为可见，表示数据正在加载

        OkHttpClient client = new OkHttpClient();
        //创建一个 OkHttp 客户端实例，用于发送 HTTP 请求
        String url = "https://www.omdbapi.com/?apikey=" + API_KEY + "&s=" + query;
        //s参数表示搜索关键词，apikey 是从 OMDb API 获取的密钥

        Request request = new Request.Builder().url(url).build();
        //构建一个请求对象，设置请求的 URL

        //发送异步网络请求
        //异步请求不会阻塞主线程，用户可以继续操作界面
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 请求失败：切换回主线程，隐藏加载动画并提示错误
                runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE); //隐藏加载动画
                    Toast.makeText(MainActivity.this, "请求失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onResponse(Call call, Response response) {
                // 请求成功：解析 JSON 数据
                try {
                    String jsonData = response.body().string();
                    JSONObject jsonObject = new JSONObject(jsonData);

                    if (jsonObject.has("Search")) {
                        JSONArray searchArray = jsonObject.getJSONArray("Search");

                        // 清空旧数据并添加新数据
                        movies.clear();
                        for (int i = 0; i < searchArray.length(); i++) {
                            JSONObject movieObject = searchArray.getJSONObject(i);
                            String title = movieObject.getString("Title");
                            String year = movieObject.getString("Year");
                            String poster = movieObject.getString("Poster");
                            movies.add(new Movie(title, year, poster));
                        }

                        // 切换回主线程更新 UI
                        runOnUiThread(() -> {
                            progressBar.setVisibility(View.GONE);
                            movieAdapter.notifyDataSetChanged();
                        });
                    } else {
                        // 未找到影片：切换回主线程提示用户
                        runOnUiThread(() -> {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, "未找到影片", Toast.LENGTH_SHORT).show();
                        });
                    }
                } catch (Exception e) {
                    // 解析异常：切换回主线程提示用户
                    runOnUiThread(() -> {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "数据解析错误", Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });
    }
}