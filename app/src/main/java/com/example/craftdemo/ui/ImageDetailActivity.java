package com.example.craftdemo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.craftdemo.R;
import com.squareup.picasso.Picasso;

public class ImageDetailActivity extends AppCompatActivity {

    private ImageView mView;
    private TextView mAuthor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        mView = findViewById(R.id.imageView);
        mAuthor = findViewById(R.id.textView);

        setUp();
    }

    private void setUp() {
        Intent intent = getIntent();
        if(intent != null) {
            String author = intent.getStringExtra("author");
            String url = intent.getStringExtra("url");
            int width = intent.getIntExtra("width", 500);
            int height = intent.getIntExtra("height", 500);
            mAuthor.setText(author);
            Picasso.get()
                    .load(url)
                    .resize(width, height)
                    .into(mView);
        }
    }
}