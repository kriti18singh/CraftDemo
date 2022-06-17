package com.example.craftdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.craftdemo.model.ImageResult;
import com.example.craftdemo.network.ImageRepository;
import com.example.craftdemo.ui.adapter.ImageAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mMainView;
    private ImageAdapter mImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainView = findViewById(R.id.mainRecyclerView);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        mMainView.setLayoutManager(layoutManager);

        MainActivityViewModel model = new ViewModelProvider(this, new MainActivityViewModelFactory(ImageRepository.getInstance())).get(MainActivityViewModel.class);

        model.getImages().observe(this, new Observer<List<ImageResult>>() {
            @Override
            public void onChanged(@Nullable List<ImageResult> imageList) {
                mImageAdapter = new ImageAdapter( MainActivity.this, imageList);
                mMainView.setAdapter(mImageAdapter);
            }
        });
    }
}