package com.example.craftdemo.model;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ImageResult {
    @SerializedName("id")
    @PrimaryKey
    @NonNull
    private String id;

    @ColumnInfo(name = "author")
    @SerializedName("author")
    private String author;

    @SerializedName("width")
    @ColumnInfo(name = "width")
    private Integer width;

    @SerializedName("height")
    @ColumnInfo(name = "height")
    private Integer height;

    @SerializedName("url")
    @ColumnInfo(name = "url")
    private String url;

    @SerializedName("download_url")
    @ColumnInfo(name = "download_url")
    private String downloadUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
