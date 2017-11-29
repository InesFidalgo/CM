package com.example.gonca.test;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

/**
 * Created by gonca on 27/11/2017.
 */

@Dao
public interface CounterDao {
    @Query("SELECT * FROM counter LIMIT 1")
    Counter getCounter();

    @Insert
    void insert(Counter counter);

    @Update
    void updateCounter(Counter c);

    @Delete
    void delete(Counter counter);
}