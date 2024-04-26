package com.example.petcentertwo.presenter

import android.app.Application
import android.content.Context

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChanel()

    }

    private fun createNotificationChanel() {
       if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                val channel = android.app.NotificationChannel(
                    CounterNotificationService.COUNTER_CHANEL_ID,
                    "Counter",
                    android.app.NotificationManager.IMPORTANCE_DEFAULT
                )
                channel.description = "Counter Notification"

           val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
    }
}