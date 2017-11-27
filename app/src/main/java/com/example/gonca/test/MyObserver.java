package com.example.gonca.test;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;

import static android.content.ContentValues.TAG;

/**
 * Demonstrates usage of basic LifecycleObserver.
 */
public class MyObserver implements LifecycleObserver {
    private static final String LOG_TAG = MyObserver.class.getSimpleName();
    private Activity activity;
    AppDatabase db;
    Counter c;
    MyObserver(Activity act){
        this.activity=act;



        db= Room.databaseBuilder(act.getApplicationContext(),
                AppDatabase.class, "database-name2").allowMainThreadQueries().build();


        try {
            c = db.counterDao().getCounter();

            Log.d(TAG, "MyObserver: ");
            Log.d(TAG, "MyObserver: " + c.getValue());

            if (c == null) {
                c = new Counter();
                c.setValue(0);
                db.counterDao().insert(c);

            }
        }
        catch(Exception e){
            c = new Counter();
            c.setValue(0);
            db.counterDao().insert(c);

        }


        liveDataTimerViewModel = ViewModelProviders.of((FragmentActivity) activity).get(LiveDataTimerViewModel.class);
        liveDataTimerViewModel.setValue((int) c.getValue());
    }

    private LiveDataTimerViewModel liveDataTimerViewModel;

    @BindView(R.id.timer_value_text)
    protected TextView timerValueText;

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {

        liveDataTimerViewModel.countElapsedTime(true);

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        liveDataTimerViewModel.countElapsedTime(false);
        c.setValue(liveDataTimerViewModel.getValue());

        Log.d(TAG, "MyObservering: " + c.getValue());
        db.counterDao().updateCounter(c);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        c.setValue(liveDataTimerViewModel.getValue());

        Log.d(TAG, "MyObservering: " + c.getValue());
        db.counterDao().updateCounter(c);
    }
}