package chapter.android.aweme.ss.com.homework.Exercises1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import chapter.android.aweme.ss.com.homework.R;

public class Exercises1 extends AppCompatActivity {

    private static final String TAG = "PAD";

    private static final String ON_CREATE = "onCreate1";
    private static final String ON_START = "onStart1";
    private static final String ON_RESUME = "onResume1";
    private static final String ON_PAUSE = "onPause1";
    private static final String ON_STOP = "onStop1";
    private static final String ON_RESTART = "onRestart1";
    private static final String ON_DESTROY = "onDestroy1";
    private static final String ON_SAVE_INSTANCE_STATE = "onSaveInstanceState1";
    private static final String ON_RESTORE_INSTANCE_STATE = "onRestoreInstanceState1";
    private static final String LIFECYCLE_CALLBACKS_TEXT_KEY = "callbacks1";

    private TextView mLifecycleDisplay;
    private static boolean destroyFlag = false;

    public void showDialog(View view) {
        startActivity(new Intent(this, DialogActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex1);
        mLifecycleDisplay = findViewById(R.id.logcat);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(LIFECYCLE_CALLBACKS_TEXT_KEY)) {
                String savedContent = (String) savedInstanceState.get(LIFECYCLE_CALLBACKS_TEXT_KEY);
                mLifecycleDisplay.setText(savedContent);
                if (destroyFlag) {
                    logAndAppend(ON_STOP);
                    logAndAppend(ON_DESTROY);
                    destroyFlag = false;
                }
            }
        }
        logAndAppend(ON_CREATE);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logAndAppend(ON_RESTART);
    }

    @Override
    protected void onStart() {
        super.onStart();
        logAndAppend(ON_START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        logAndAppend(ON_RESUME);
    }


    @Override
    protected void onPause() {
        super.onPause();
        logAndAppend(ON_PAUSE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        logAndAppend(ON_STOP);
    }

    @Override
    protected void onDestroy() {
        destroyFlag = true;
        super.onDestroy();
        logAndAppend(ON_DESTROY);
    }

    private void logAndAppend(String lifecycleEvent) {
        Log.d(TAG, "Lifecycle Event: " + lifecycleEvent);
        mLifecycleDisplay.append(lifecycleEvent + "\n");
    }

    public void resetLifecycleDisplay(View view) {
        mLifecycleDisplay.setText("Lifecycle callbacks:\n");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        logAndAppend(ON_SAVE_INSTANCE_STATE);
        String content = mLifecycleDisplay.getText().toString();//当前已有的log 提取出来
        outState.putString(LIFECYCLE_CALLBACKS_TEXT_KEY, content); //把内容存储起来
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        logAndAppend(ON_RESTORE_INSTANCE_STATE);
    }
}
