package com.example.testkotlin

import android.os.Bundle
import android.util.Log

import com.unity3d.player.UnityPlayerActivity

class MyUnityPlayerActivity : UnityPlayerActivity() {
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Running MyUnityPlayerActivity.")
        BackgroundCheckScheduler.scheduleBackgroundCheck(this)
    }

    companion object {
        private const val TAG = "Player"
    }
}