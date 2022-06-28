package com.example.craftdemo.database;

import com.example.craftdemo.model.ImageResult;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ImageDao {
    @Query("SELECT * FROM ImageResult")
    List<ImageResult> getAll();

    @Insert
    void insertAll(List<ImageResult> users);

    @Delete
    void delete(ImageResult image);
}
