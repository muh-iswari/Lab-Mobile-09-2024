package com.example.tugaspraktikum6;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ApiService apiService;
    private RecyclerView recyclerView;
    private List<User> users;
    private int currentPage = 1;
    private UserAdapter userAdapter;
    private LinearLayout homeMain;
    private View homeLoading, loadMoreLoading;
    private Button loadMoreBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = RetrofitClient.getClient().create(ApiService.class);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        homeMain = findViewById(R.id.homeMain);
        homeLoading = findViewById(R.id.homeLoading);
        loadMoreLoading = findViewById(R.id.loadMoreLoading);
        loadMoreBtn = findViewById(R.id.loadMoreBtn);

        Handler handler = new Handler(Looper.getMainLooper());
        ExecutorService executor = Executors.newSingleThreadExecutor();

        FragmentManager fragmentManager = getSupportFragmentManager();
        ErrorNoConnectionFragment errorNoConnectionFragment = new ErrorNoConnectionFragment();
        Fragment fragment = fragmentManager.findFragmentByTag(ErrorNoConnectionFragment.class.getSimpleName());

        homeLoading.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Call<UserResponse> call = apiService.getUsers(currentPage, 6);
                call.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.isSuccessful()) {
                            currentPage++;
                            users = response.body().getData();
                            userAdapter = new UserAdapter(MainActivity.this, users);
                            recyclerView.setAdapter(userAdapter);

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.setVisibility(View.VISIBLE);
                                    homeLoading.setVisibility(View.GONE);
                                }
                            });
                        } else {
                            homeMain.setVisibility(View.GONE);
                            if (!(fragment instanceof ErrorNoConnectionFragment)) {
                                fragmentManager.beginTransaction()
                                        .replace(R.id.homeScreen, errorNoConnectionFragment)
                                        .addToBackStack(null)
                                        .commit();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        homeMain.setVisibility(View.GONE);
                        if (!(fragment instanceof ErrorNoConnectionFragment)) {
                            fragmentManager.beginTransaction()
                                    .replace(R.id.homeScreen, errorNoConnectionFragment)
                                    .addToBackStack(null)
                                    .commit();
                        }
                    }
                });

            }
        });

        loadMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMoreBtn.setVisibility(View.GONE);
                loadMoreLoading.setVisibility(View.VISIBLE);

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        Call<UserResponse> call = apiService.getUsers(currentPage, 6);
                        call.enqueue(new Callback<UserResponse>() {
                            @Override
                            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                if (response.isSuccessful()) {
                                    users.addAll(response.body().getData());
                                    userAdapter = new UserAdapter(MainActivity.this, users);
                                    recyclerView.setAdapter(userAdapter);

                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            loadMoreLoading.setVisibility(View.GONE);
                                        }
                                    });
                                } else {
                                    homeMain.setVisibility(View.GONE);
                                    if (!(fragment instanceof ErrorNoConnectionFragment)) {
                                        fragmentManager.beginTransaction()
                                                .replace(R.id.homeScreen, errorNoConnectionFragment)
                                                .addToBackStack(null)
                                                .commit();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<UserResponse> call, Throwable t) {
                                homeMain.setVisibility(View.GONE);
                                if (!(fragment instanceof ErrorNoConnectionFragment)) {
                                    fragmentManager.beginTransaction()
                                            .replace(R.id.homeScreen, errorNoConnectionFragment)
                                            .addToBackStack(null)
                                            .commit();
                                }
                            }
                        });
                    }
                });
            }
        });
    }
}