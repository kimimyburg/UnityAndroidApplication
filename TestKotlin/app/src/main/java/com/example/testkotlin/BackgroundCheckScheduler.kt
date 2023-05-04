import android.content.Context
import android.util.Log
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.testkotlin.BackgroundCheckWorker
import com.example.testkotlin.MyUnityPlayerActivity
import java.util.concurrent.TimeUnit

class BackgroundCheckScheduler
{
    companion object {
        private const val TAG = "Player"
        private const val BACKGROUND_CHECK_WORK_TAG = "background_check_work"
        private const val BACKGROUND_CHECK_INTERVAL_MINUTES = 15L

        fun scheduleBackgroundCheck(context: Context) {
            Log.d(TAG, "Running MyUnityPlayerActivity.")

//            val backgroundCheckWork = PeriodicWorkRequestBuilder<BackgroundCheckWorker>(
//                BACKGROUND_CHECK_INTERVAL_MINUTES,
//                TimeUnit.MINUTES
//            )
//                .addTag(BACKGROUND_CHECK_WORK_TAG)
//                .build()
//
//            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
//                BACKGROUND_CHECK_WORK_TAG,
//                ExistingPeriodicWorkPolicy.REPLACE,
//                backgroundCheckWork
//            )
        }
    }
}