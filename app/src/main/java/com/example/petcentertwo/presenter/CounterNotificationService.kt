package com.example.petcentertwo.presenter

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.petcentertwo.R
import com.example.petcentertwo.presenter.activity.MainActivity
import java.nio.file.attribute.AclEntry.Builder

class CounterNotificationService(
    private val context: Context
) {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
    fun showNotification(counter: Int) {
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PendingIntent.FLAG_IMMUTABLE else 0)
        val incrementIntent = PendingIntent.getBroadcast(
            context,
            2,
            Intent(context, CounterNotificationReceiver::class.java),
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PendingIntent.FLAG_IMMUTABLE else 0
        )
        val notification = NotificationCompat.Builder(context, COUNTER_CHANEL_ID)
            .setContentTitle("Counter")
            .setContentText("Counter is $counter")
            .setSmallIcon(R.drawable.ic_baseline_baby_changing_station_24)
            .setContentIntent(activityPendingIntent)
            .addAction(R.drawable.ic_baseline_baby_changing_station_24, "Increment", incrementIntent)
            .build()

        notificationManager.notify(1, notification)
    }

    fun showBirthdayNotification(petName: String,petAge: Int,) {
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PendingIntent.FLAG_IMMUTABLE else 0
        )

        val notification = NotificationCompat.Builder(context, COUNTER_CHANEL_ID)
            .setContentTitle("Aniversário de $petName")
            .setContentText("O $petName está completando $petAge anos! Parabéns!")
            .setSmallIcon(R.drawable.ic_baseline_baby_changing_station_24)
            .setContentIntent(activityPendingIntent)
            .build()

        notificationManager.notify(2, notification)
    }

    companion object {
        const val COUNTER_CHANEL_ID = "counter_channel"
    }
}