package com.example.ana_f.workwork;

import android.arch.lifecycle.ViewModel;

/**
 * Created by ana_f on 27/11/2017.
 */

public class ClickCounterViewModel extends ViewModel{

    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
