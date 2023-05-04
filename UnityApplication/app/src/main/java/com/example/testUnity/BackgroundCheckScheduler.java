package com.example.testUnity;

import android.content.Context;
import android.util.Log;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.example.testUnity.BackgroundCheckWorker;
import java.util.concurrent.TimeUnit;

public class BackgroundCheckScheduler {
    static String BACKGROUND_CHECK_WORK_TAG = "background_check_work";
    static long BACKGROUND_CHECK_INTERVAL_MINUTES = 15L;

    static void scheduleBackgroundCheck(Context context) {
        PeriodicWorkRequest backgroundCheckWork =
            new PeriodicWorkRequest.Builder(BackgroundCheckWorker.class, BACKGROUND_CHECK_INTERVAL_MINUTES, TimeUnit.MINUTES)
            .addTag(BACKGROUND_CHECK_WORK_TAG).build();

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                BACKGROUND_CHECK_WORK_TAG,
                ExistingPeriodicWorkPolicy.REPLACE,
                backgroundCheckWork
        );
    }
}
