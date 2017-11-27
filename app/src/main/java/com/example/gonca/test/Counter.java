package com.example.gonca.test;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by gonca on 27/11/2017.
 */

@Entity
public class Counter {
    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "value")
    private long value;


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}