package com.example.testkotlin

import android.content.Context
import android.os.BatteryManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import java.util.*

class BackgroundCheckWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams)
{
    private val url = "http://196.168.210.233:5109/Connection"
    private val hubConnection: HubConnection = HubConnectionBuilder.create(url).build()

    override fun doWork(): Result {

        //Check battery
        val batteryLevel = getPicoBatteryLevel()
        val batteryManager = applicationContext?.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        val batteryStatus = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_STATUS)
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        // Send battery level to API
        sendBatteryLevelToApiUsingSignalR(batteryLevel, batteryStatus)
        return Result.success()
    }

    private fun getPicoBatteryLevel(): Int? {
        // Retrieve the headset's battery level
        return getBatteryPercentage(applicationContext)
    }

    private fun getBatteryPercentage(context: Context?): Int? {
        val bm = context?.getSystemService(AppCompatActivity.BATTERY_SERVICE) as BatteryManager
        return bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    }

    private fun sendBatteryLevelToApiUsingSignalR(batteryLevel: Int?, batteryStatus: Int) {
        hubConnection.onClosed {
            println("Connection closed: ${it.message}")
            // Attempt to restart the connection
            Thread.sleep(Random().nextInt(5) * 1000L)
            hubConnection.start()
        }

       val e = hubConnection.start().doOnComplete {
            try {
                hubConnection.invoke("SendBatteryStats", batteryLevel)
                println("Stats sent")
            } catch (ex: Exception) {
                println("Error sending stats: $ex")
            } finally {
                hubConnection.stop()
                println("Connection stopped")
            }
        }.blockingAwait()
    }
}