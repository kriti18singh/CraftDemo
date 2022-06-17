package com.example.craftdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.craftdemo.R;
import com.example.craftdemo.model.ImageResult;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private final Context mContext;
    private final List<ImageResult> mList;

    public ImageAdapter(Context mContext, List<ImageResult> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_image_info, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        ImageResult image = mList.get(position);
        if (image.getDownloadUrl() != null) {
            Picasso.get().load(image.getDownloadUrl()).into(holder.ivImage);
        } else {
            Picasso.get().load(R.drawable.ic_launcher_foreground).into(holder.ivImage);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivImage;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
        }
    }
}
