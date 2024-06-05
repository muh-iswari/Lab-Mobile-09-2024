package com.example.tugaspraktikum6;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailActivity extends AppCompatActivity {
    private ApiService apiService;
    private User user;
    private LinearLayout userDetailMain;
    private View userDetailLoading;
    private LinearLayout userDetailContainer;
    private ImageView detailAvatarImageView;
    private TextView detailNameTextView, detailEmailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        apiService = RetrofitClient.getClient().create(ApiService.class);

        userDetailMain = findViewById(R.id.userDetailMain);
        userDetailLoading = findViewById(R.id.userDetailLoading);
        userDetailContainer = findViewById(R.id.userDetailContainer);
        detailAvatarImageView = findViewById(R.id.detailAvatarImageView);
        detailNameTextView = findViewById(R.id.detailNameTextView);
        detailEmailTextView = findViewById(R.id.detailEmailTextView);

//        Handler handler = new Handler(Looper.getMainLooper());
        ExecutorService executor = Executors.newSingleThreadExecutor();

        FragmentManager fragmentManager = getSupportFragmentManager();
        ErrorNoConnectionFragment errorNoConnectionFragment = new ErrorNoConnectionFragment();
        Fragment fragment = fragmentManager.findFragmentByTag(ErrorNoConnectionFragment.class.getSimpleName());

        int userId = getIntent().getIntExtra("user_id", -1);

        if (userId != -1) {
            userDetailLoading.setVisibility(View.VISIBLE);
            userDetailContainer.setVisibility(View.GONE);
            executor.execute(() -> {
                Call<UserDetailResponse> call = apiService.getUserById(userId);
                call.enqueue(new Callback<UserDetailResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<UserDetailResponse> call, @NonNull Response<UserDetailResponse> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            user = response.body().getData();

                            Picasso.get().load(user.getAvatar()).into(detailAvatarImageView);
                            String fullName = user.getFirst_name() + " " + user.getLast_name();
                            detailNameTextView.setText(fullName);
                            detailEmailTextView.setText(user.getEmail());

                            userDetailContainer.setVisibility(View.VISIBLE);
                            userDetailLoading.setVisibility(View.GONE);
                        } else {
                            userDetailMain.setVisibility(View.GONE);
                            if (!(fragment instanceof ErrorNoConnectionFragment)) {
                                fragmentManager.beginTransaction()
                                        .replace(R.id.userDetailScreen, errorNoConnectionFragment)
                                        .addToBackStack(null)
                                        .commit();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<UserDetailResponse> call, @NonNull Throwable t) {
                        userDetailMain.setVisibility(View.GONE);
                        if (!(fragment instanceof ErrorNoConnectionFragment)) {
                            fragmentManager.beginTransaction()
                                    .replace(R.id.userDetailScreen, errorNoConnectionFragment)
                                    .addToBackStack(null)
                                    .commit();
                        }
                    }
                });
            });
        }
    }
}