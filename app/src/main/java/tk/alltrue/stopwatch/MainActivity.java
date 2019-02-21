package tk.alltrue.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int mSeconds = 0;
    private boolean mIsRunning;
    private boolean mIsWasRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            mSeconds = savedInstanceState.getInt("seconds");
            mIsRunning = savedInstanceState.getBoolean("isRunning");
            mIsWasRunning = savedInstanceState.getBoolean("isWasRunning");
        }
        runTimer();
    }

    public void onStartClick(View view) {
        mIsRunning = true;
    }

    public void onStopCLick(View view) {
        mIsRunning = false;
    }

    public void onResetClick(View view) {
        mIsRunning = false;
        mSeconds = 0;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("seconds", mSeconds);
        outState.putBoolean("isRunning", mIsRunning);
        outState.putBoolean("isWasRunning", mIsWasRunning);
    }
    private void runTimer() {
        final TextView timeTextView = (TextView) findViewById(R.id.textViewTime);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = mSeconds/3600;
                int minutes = (mSeconds % 3600)/60;
                int secs = (mSeconds % 3600) % 60;

                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeTextView.setText(time);
                if(mIsRunning) {
                    mSeconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mIsWasRunning = mIsRunning;
        mIsRunning = false;
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mIsWasRunning) {
            mIsRunning = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        mIsWasRunning = mIsRunning;
        mIsRunning = false;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mIsWasRunning) {
            mIsRunning = true;
        }
    }
}
