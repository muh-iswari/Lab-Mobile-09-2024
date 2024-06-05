package com.example.tugaspraktikum6;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ErrorNoConnectionFragment extends Fragment {
    private ApiService apiService;
    private LinearLayout errorNoConnectionMain;
    private View errorNoConnectionLoading;
    private Button errorRetryBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_error_no_connection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiService = RetrofitClient.getClient().create(ApiService.class);

        Handler handler = new Handler(Looper.getMainLooper());
        ExecutorService executor = Executors.newSingleThreadExecutor();

        errorNoConnectionMain = view.findViewById(R.id.errorNoConnectionMain);
        errorNoConnectionLoading = view.findViewById(R.id.errorNoConnectionLoading);
        errorRetryBtn = view.findViewById(R.id.errorRetryBtn);

        errorRetryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errorNoConnectionLoading.setVisibility(View.VISIBLE);
                errorNoConnectionMain.setVisibility(View.GONE);

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(500);

                            Call<UserResponse> call = apiService.getUsers(1, 6);
                            call.enqueue(new Callback<UserResponse>() {
                                @Override
                                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                    if (response.isSuccessful()) {
                                        getActivity().getSupportFragmentManager().beginTransaction().remove(ErrorNoConnectionFragment.this).commit();
                                        getActivity().recreate();
                                    } else {
                                        errorNoConnectionMain.setVisibility(View.VISIBLE);
                                        errorNoConnectionLoading.setVisibility(View.GONE);
                                    }
                                }

                                @Override
                                public void onFailure(Call<UserResponse> call, Throwable t) {
                                    errorNoConnectionMain.setVisibility(View.VISIBLE);
                                    errorNoConnectionLoading.setVisibility(View.GONE);
                                }
                            });
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        });
    }
}