package com.example.demo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        findViewById(R.id.record).setOnClickListener(this);
        findViewById(R.id.photo).setOnClickListener(this);
        findViewById(R.id.container).setOnClickListener(this);
        init();
    }

    private final BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(final Context context, final Intent intent) {
            final String action = intent.getAction();
            if (Intent.ACTION_SCREEN_OFF.equals(action)) {
                abortBroadcast();
                go();
            }
        }
    };

    private void init() {
        final IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mBatInfoReceiver, filter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.record:
                AudioRecord audioRecord = new AudioRecord(this);
                audioRecord.startRecording();
                break;
            case R.id.photo:
                go();
                break;
            case R.id.container:
                go();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        go();
    }

    public void go() {
        Intent intent = new Intent(this, PhotoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
