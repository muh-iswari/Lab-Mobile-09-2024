package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageView ivProfile = findViewById(R.id.iv_profile);
        TextView tvProfile = findViewById(R.id.Tv_profile);
        TextView tvAngkaFollowers = findViewById(R.id.Tv_followers);
        TextView tvAngkaFollowing = findViewById(R.id.Tv_following);
        ImageView ivPost = findViewById(R.id.iv_post);

        Intent intent = getIntent();

        Instagram instagram1 = intent.getParcelableExtra("datainstagram");

        ivProfile = findViewById(R.id.iv_profile);
        ivPost = findViewById(R.id.iv_post);
        tvProfile = findViewById(R.id.Tv_profile);
        tvAngkaFollowers = findViewById(R.id.Tv_followers);
        tvAngkaFollowing = findViewById(R.id.Tv_following);

        ivProfile.setImageResource(instagram1.getFotoProfile());
        ivPost.setImageResource(instagram1.getFotoPostingan());
        tvProfile.setText(instagram1.getUsername());
        tvAngkaFollowers.setText(String.valueOf(instagram1.getFollowers()));
        tvAngkaFollowing.setText(String.valueOf(instagram1.getFollowing()));

        ivPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, PostinganActivity.class);
                intent.putExtra("datainstagram",instagram1);
                startActivity(intent);
            }
        });

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, StoryActivity.class);
                intent.putExtra("datainstagram", instagram1);
                startActivity(intent);
            }
        });
    }
}
