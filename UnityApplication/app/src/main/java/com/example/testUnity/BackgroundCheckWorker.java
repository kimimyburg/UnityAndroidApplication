package com.example.testUnity;

import android.content.Context;
import android.os.BatteryManager;
import android.os.Build;
import android.util.Log;

import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import com.microsoft.signalr.OnClosedCallback;
import java.util.*;
import io.reactivex.rxjava3.functions.Action;

public class BackgroundCheckWorker extends Worker
{
    private static final String TAG = "BACKGROUND CHECK";

    private String url = "http://192.168.50.61:5109/Connection";
    private HubConnection hubConnection = HubConnectionBuilder.create(url).build();

    public BackgroundCheckWorker(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
    }

    public Result doWork() {
        //Check battery
        int batteryLevel = getPicoBatteryLevel();
        BatteryManager batteryManager = (BatteryManager) getApplicationContext().getSystemService(Context.BATTERY_SERVICE);
        int batteryStatus = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            batteryStatus = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_STATUS);
        }

        // Send battery level to API
        sendBatteryLevelToApiUsingSignalR(batteryLevel, batteryStatus);
        return Result.success();
    }

    private int getPicoBatteryLevel() {
        // Retrieve the headset's battery level
        return getBatteryPercentage(getApplicationContext());
    }

    private int getBatteryPercentage(Context context) {
        BatteryManager batteryManager = (BatteryManager) getApplicationContext().getSystemService(Context.BATTERY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        }
        return 0;
    }

    private void sendBatteryLevelToApiUsingSignalR(int batteryLevel, int batteryStatus)
    {
        Log.d(TAG, "BACKGROUND SERVICE CHECKER.");

        hubConnection.onClosed(new OnClosedCallback() {
            @Override
            public void invoke(Exception exception) {
                try {
                    Thread.sleep(new Random().nextInt(5) * 1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                hubConnection.start();
            }
        });

        hubConnection.start().doOnComplete(new Action() {
            @Override
            public void run() throws Throwable {
                try {
                    hubConnection.invoke("SendBatteryStats", batteryLevel);
                } catch(Exception e)
                {
                    System.out.print("Error sending stats: $ex");
                }
                finally {
                    hubConnection.stop();
                }
            }
        }).blockingAwait();
    }
}
