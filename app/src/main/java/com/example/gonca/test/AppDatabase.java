package com.example.gonca.test;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by gonca on 27/11/2017.
 */

@Database(entities = {Counter.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CounterDao counterDao();
}