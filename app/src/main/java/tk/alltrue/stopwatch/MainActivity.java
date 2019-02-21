package tk.alltrue.stopwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private int mSeconds = 0;
    private boolean mIsRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

}
