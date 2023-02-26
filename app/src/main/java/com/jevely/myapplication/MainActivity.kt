package com.jevely.myapplication

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tool = NotificationTool()

        findViewById<TextView>(R.id.textview).setOnClickListener {
            tool.createNormalNotification(this, false)
        }

        findViewById<TextView>(R.id.textview2).setOnClickListener {
            tool.createNormalNotification(this, true)
        }

        findViewById<TextView>(R.id.textview3).setOnClickListener {
            tool.createMessageNotification(this, false)
        }

        findViewById<TextView>(R.id.textview4).setOnClickListener {
            tool.createHasTaskNotification(this, false)
        }

        findViewById<TextView>(R.id.textview5).setOnClickListener {
            tool.createBigPicNotification(this, false)
        }

        findViewById<TextView>(R.id.textview6).setOnClickListener {
            tool.createRemoteViewNotification(this, true)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(Intent(this, NotificationService::class.java))
        } else {
            startService(Intent(this, NotificationService::class.java))
        }

    }
}