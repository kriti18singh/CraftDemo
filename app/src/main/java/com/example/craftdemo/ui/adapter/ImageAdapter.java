package com.example.craftdemo.ui.adapter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.craftdemo.R;
import com.example.craftdemo.model.ImageResult;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>
implements OnItemClick {

    @Override
    public void onItemClicked(int position) {
        ImageResult image = mList.get(position);
        mListener.onImageClicked(image);
    }

    public interface OnImageClickListener {
        void onImageClicked(ImageResult result);
    }

    private final Context mContext;
    private List<ImageResult> mList;
    private final OnImageClickListener mListener;
    private final ConnectivityManager mConnectivityManager;
    private final Picasso picasso;

    public void setList(List<ImageResult> imageList) {
        mList = imageList;
    }

    public ImageAdapter(Context mContext, List<ImageResult> mList,
                        OnImageClickListener listener,
                        ConnectivityManager manager,
                        Picasso picasso) {
        this.mContext = mContext;
        this.mList = mList;
        this.mListener = listener;
        this.mConnectivityManager = manager;
        this.picasso = picasso;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_image_info, parent, false);
        return new ImageViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        ImageResult image = mList.get(position);
        if (image.getDownloadUrl() != null) {
            //TODO remove later, for debugging only
            //Picasso.get().setIndicatorsEnabled(true);





    picasso.load(image.getDownloadUrl())
            .networkPolicy(NetworkPolicy.OFFLINE)
            .placeholder(R.drawable.ic_launcher_foreground)
            .fit()
            .into(holder.ivImage, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    picasso.load(image.getDownloadUrl())
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .error(R.drawable.ic_launcher_foreground)
                            .into(holder.ivImage);
                }
            });
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

        public ImageViewHolder(View itemView, OnItemClick listener ) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(getBindingAdapterPosition());
                }
            });
        }
    }
}
