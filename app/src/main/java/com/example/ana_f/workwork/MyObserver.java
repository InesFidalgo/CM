package com.example.ana_f.workwork;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by ana_f on 27/11/2017.
 */

public class MyObserver implements LifecycleObserver {
    private static final String LOG_TAG = MyObserver.class.getSimpleName();
    private LiveDataTimerViewModel liveDataTimerViewModel;

    MutableLiveData<Long> actual;

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {

        Log.d(LOG_TAG, "resumed observing lifecycle.");

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {

        Log.d(LOG_TAG, "paused observing lifecycle.");

    }

}
