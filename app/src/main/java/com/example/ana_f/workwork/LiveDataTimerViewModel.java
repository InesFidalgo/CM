package com.example.ana_f.workwork;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ana_f on 27/11/2017.
 */

public class LiveDataTimerViewModel extends ViewModel{
    private static final int ONE_SECOND = 1000;
    private long actual;
    public MutableLiveData<Long> elapsedTime = new MutableLiveData<>();

    public long initialTime;

    public LiveDataTimerViewModel() {
        initialTime = SystemClock.elapsedRealtime();
        Timer timer = new Timer();

        // Update the elapsed time every second.
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                // setValue() cannot be called from a background thread so post to main thread.
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        final long newValue = (SystemClock.elapsedRealtime() - initialTime) / 1000;
                        elapsedTime.setValue(newValue);

                    }



                });


            }
        }, ONE_SECOND, ONE_SECOND);
    }



    public LiveData<Long> getElapsedTime() {
        return elapsedTime;
    }
}
