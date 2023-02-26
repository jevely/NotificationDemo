package com.jevely.myapplication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * service
 */
class NotificationService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("LJW", "服务启动了")

        val notificationTool = NotificationTool()

        startForeground(100001, notificationTool.getNormalNotificationBuilder(this))
    }

}