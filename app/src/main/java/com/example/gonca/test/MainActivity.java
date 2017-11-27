package com.example.gonca.test;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends LifecycleActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();



    private LiveDataTimerViewModel liveDataTimerViewModel;

    private final Observer<Long> elapsedTimeObserver = new Observer<Long>() {
        @Override
        public void onChanged(@Nullable final Long newValue) {
            String newText = ""+ newValue+"seconds";
            displayTimerValue(newText);
            Log.d(LOG_TAG, "Updating timer");
        }
    };

    @BindView(R.id.timer_value_text)
    protected TextView timerValueText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);









        ButterKnife.bind(this);
        // Create the observer which updates the UI.
        getLifecycle().addObserver(new MyObserver(this));


        liveDataTimerViewModel = ViewModelProviders.of(this).get(LiveDataTimerViewModel.class);

        subscribeElapsedTimeObserver();
    }

    private void subscribeElapsedTimeObserver() {
        liveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimeObserver);
    }

    private void displayTimerValue(String value) {
        timerValueText.setText(String.valueOf(value));
    }
}