package com.example.ana_f.workwork;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ana_f on 27/11/2017.
 */

public class LiveDataDemoActivity extends LifecycleActivity {

    private static final String LOG_TAG = LiveDataDemoActivity.class.getSimpleName();

    private LiveDataTimerViewModel liveDataTimerViewModel;
    MutableLiveData<Long> actual;

    private final Observer<Long> elapsedTimeObserver = new Observer<Long>() {
        @Override
        public void onChanged(@Nullable final Long newValue) {
            //String newText = LiveDataDemoActivity.this.getResources().getString(R.string.seconds, newValue);


            displayTimerValue("boy"+ newValue.toString());
            Log.d(LOG_TAG, "Updating timer");
        }
    };

    @BindView(R.id.timer_value_text)
    protected TextView timerValueText;

    public LiveDataDemoActivity() {
        actual = null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ButterKnife.bind(this);

        liveDataTimerViewModel = ViewModelProviders.of(this).get(LiveDataTimerViewModel.class);

        subscribeElapsedTimeObserver();
        getLifecycle().addObserver(new MyObserver());
    }





    private void subscribeElapsedTimeObserver() {
        liveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimeObserver);
    }

    private void displayTimerValue(String value) {
        timerValueText.setText(String.valueOf(value));
    }
}
