package com.example.craftdemo.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.craftdemo.CompositionRoot;
import com.example.craftdemo.CustomApplication;
import com.example.craftdemo.R;
import com.example.craftdemo.model.ImageResult;
import com.example.craftdemo.ui.adapter.ImageAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ImageAdapter.OnImageClickListener {

    private RecyclerView mMainView;
    private ImageAdapter mImageAdapter;

    public CompositionRoot getCompositionRoot() {
        return ((CustomApplication) getApplication()).getCompositionRoot();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainView = findViewById(R.id.mainRecyclerView);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        mMainView.setLayoutManager(layoutManager);

        MainActivityViewModel model = new ViewModelProvider(
                this,
                new MainActivityViewModelFactory(
                        getCompositionRoot().getDatabase(),
                        getCompositionRoot().getImagesApi(),
                        getCompositionRoot().getConnectivityManager()
                )).get(MainActivityViewModel.class);

        model.getImages().observe(this, new Observer<List<ImageResult>>() {
            @Override
            public void onChanged(@Nullable List<ImageResult> imageList) {
                mImageAdapter = new ImageAdapter( MainActivity.this, imageList, MainActivity.this);
                mMainView.setAdapter(mImageAdapter);
            }
        });
    }

    @Override
    public void onImageClicked(ImageResult result) {
        Intent intent = new Intent(this, ImageDetailActivity.class);
        intent.putExtra("author", result.getAuthor());
        intent.putExtra("url", result.getDownloadUrl());
        intent.putExtra("width", result.getWidth());
        intent.putExtra("height", result.getHeight());
        startActivity(intent);
    }
}