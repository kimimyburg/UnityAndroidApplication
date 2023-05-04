package com.example.testUnity;

import android.os.Bundle;
import android.util.Log;

import com.unity3d.player.UnityPlayerActivity;

public class MyUnityPlayerActivity extends UnityPlayerActivity {
    private static final String TAG = "Player";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Running MyUnityPlayerActivity.");
        BackgroundCheckScheduler.scheduleBackgroundCheck(this);
    }
}