/**
 * Created by gonca on 27/11/2017.
 */
package com.example.gonca.test;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Demonstrates LiveData functionality.
 */

public class LiveDataTimerViewModel extends ViewModel {
    private static final int ONE_SECOND = 1000;

    private MutableLiveData<Long> elapsedTime = new MutableLiveData<>();

    private long actualTime = 0;
    private boolean counting = true;
    private long addedTime = 0;
    private long newTime = 0;

    public LiveDataTimerViewModel() {

        actualTime = SystemClock.elapsedRealtime();
        Timer timer = new Timer();

        // Update the elapsed time every second.
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (counting == false) {
                    actualTime = SystemClock.elapsedRealtime();
                } else {
                    final long newValue = addedTime + ((SystemClock.elapsedRealtime() - actualTime) / 1000);
                    // setValue() cannot be called from a background thread so post to main thread.
                    addedTime = newValue;
                    actualTime = SystemClock.elapsedRealtime();
                    newTime = newValue;
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            elapsedTime.setValue(newValue);

                        }
                    });
                }
            }
        }, ONE_SECOND, ONE_SECOND);
    }

    public LiveData<Long> getElapsedTime() {
        return elapsedTime;
    }

    public void setValue(int value) {
        addedTime = value;
    }

    public long getValue() {
        return newTime;
    }

    public void countElapsedTime(boolean b) {
        counting = b;
    }
}