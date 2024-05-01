package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class StoryActivity extends AppCompatActivity {

    private ImageView iv_profile;
    private ImageView iv_story;
    private TextView tv_nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Intent intent = getIntent();

        Instagram instagram1 = intent.getParcelableExtra("datainstagram");

        iv_profile = findViewById(R.id.iv_profile2);
        iv_story = findViewById(R.id.imageStory);
        tv_nama = findViewById(R.id.tv_profile2);

        iv_profile.setImageResource(instagram1.getFotoProfile());
        iv_story.setImageResource(instagram1.getFotoStory());
        tv_nama.setText(instagram1.getUsername());

        tv_nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoryActivity.this, ProfileActivity.class);
                intent.putExtra("datainstagram", instagram1);
                startActivity(intent);
            }
        });
    }
}