package com.jevely.myapplication

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder

class NotificationTool {

    private val channelId = "CHANNEL_ID"
    private val channelName = "testPush"
    private val channelDes = "我这个是测试通知"
    private val textTitle = "测试通知"
    private val textContent = "我就是测试通知的内容"
    private val longContent =
        "我就是测试通知的内容我就是测试通知的内容我就是测试通知的内容我就是测试通知的内容我就是测试通知的内容我就是测试通知的内容我就是测试通知的内容我就是测试通知的内容我就是测试通知的内容我就是测试通知的内容我就是测试通知的内容我就是测试通知的内容"
    private val notificationId = 1

    fun getNormalNotificationBuilder(context: Context): Notification {

        createNotificationChannel(context)

        // Create an explicit intent for an Activity in your app
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(textTitle)
            .setContentText(textContent)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        builder.setContentIntent(pendingIntent)
        builder.setCategory(NotificationCompat.CATEGORY_ALARM)

        return builder.build()

    }


    fun createNormalNotification(context: Context, fullScreen: Boolean) {

        createNotificationChannel(context)

        // Create an explicit intent for an Activity in your app
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(textTitle)
            .setContentText(textContent)
            .setStyle(NotificationCompat.BigTextStyle().bigText(longContent))
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setAutoCancel(true)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        if (fullScreen) {
            val fullScreenIntent = Intent(context, MainActivity::class.java)
            val fullScreenPendingIntent = PendingIntent.getActivity(
                context, 0,
                fullScreenIntent, PendingIntent.FLAG_IMMUTABLE
            )

            builder.setFullScreenIntent(fullScreenPendingIntent, true)
            builder.setCategory(NotificationCompat.CATEGORY_MESSAGE)
        } else {
            val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
            builder.setContentIntent(pendingIntent)
            builder.setCategory(NotificationCompat.CATEGORY_ALARM)
        }

        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, builder.build())
        }

    }

    fun createBigPicNotification(context: Context, fullScreen: Boolean) {

        createNotificationChannel(context)

        // Create an explicit intent for an Activity in your app
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(textTitle)
            .setContentText(textContent)
            .setStyle(
                NotificationCompat.BigPictureStyle().bigPicture(
                    BitmapFactory.decodeResource(
                        context.resources,
                        R.drawable.testimg
                    )
                )
                    .bigLargeIcon(null)
            )
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.testimg
                )
            )
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setAutoCancel(true)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        if (fullScreen) {
            val fullScreenIntent = Intent(context, MainActivity::class.java)
            val fullScreenPendingIntent = PendingIntent.getActivity(
                context, 0,
                fullScreenIntent, PendingIntent.FLAG_IMMUTABLE
            )

            builder.setFullScreenIntent(fullScreenPendingIntent, true)
            builder.setCategory(NotificationCompat.CATEGORY_MESSAGE)
        } else {
            val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
            builder.setContentIntent(pendingIntent)
            builder.setCategory(NotificationCompat.CATEGORY_ALARM)
        }

        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, builder.build())
        }

    }

    /**
     * 可以返回首页的push
     */
    fun createHasTaskNotification(context: Context, fullScreen: Boolean) {

        createNotificationChannel(context)

        // Create an Intent for the activity you want to start
        val resultIntent = Intent(context, MainActivity2::class.java)
        // Create the TaskStackBuilder
        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
            // Add the intent, which inflates the back stack
            addNextIntentWithParentStack(resultIntent)
            // Get the PendingIntent containing the entire back stack
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(textTitle)
            .setContentText(textContent)
            .setStyle(NotificationCompat.BigTextStyle().bigText(longContent))
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setAutoCancel(true)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setContentIntent(resultPendingIntent)
            .setCategory(NotificationCompat.CATEGORY_ALARM)

        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, builder.build())
        }

    }


    fun createMessageNotification(context: Context, fullScreen: Boolean) {

        createNotificationChannel(context)

        // Create an explicit intent for an Activity in your app
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val builder = NotificationCompat.Builder(context, channelId)
            .setStyle(
                NotificationCompat.MessagingStyle("Me")
                    .setConversationTitle("Team lunch")
                    .addMessage("Hi", System.currentTimeMillis(), "111") // Pass in null for user.
                    .addMessage("What's up?", System.currentTimeMillis(), "Coworker")
                    .addMessage("Not much", System.currentTimeMillis(), "222")
                    .addMessage("How about lunch?", System.currentTimeMillis(), "Coworker")
            )
            .setSmallIcon(R.drawable.notification_icon)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setAutoCancel(true)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        if (fullScreen) {
            val fullScreenIntent = Intent(context, MainActivity::class.java)
            val fullScreenPendingIntent = PendingIntent.getActivity(
                context, 0,
                fullScreenIntent, PendingIntent.FLAG_IMMUTABLE
            )

            builder.setFullScreenIntent(fullScreenPendingIntent, true)
            builder.setCategory(NotificationCompat.CATEGORY_MESSAGE)
        } else {
            val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
            builder.setContentIntent(pendingIntent)
            builder.setCategory(NotificationCompat.CATEGORY_ALARM)
        }

        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, builder.build())
        }

    }

    private fun createNotificationChannel(context: Context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = channelName
            val descriptionText = channelDes
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createRemoteViewNotification(context: Context, hasDefaultTitle: Boolean) {

        createNotificationChannel(context)

        // Create an explicit intent for an Activity in your app
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val notificationLayout =
            RemoteViews(context.packageName, R.layout.notification_small_layout)
        val notificationLayoutExpanded =
            RemoteViews(context.packageName, R.layout.notification_large_layout)

        notificationLayout.setTextViewText(R.id.title, "text title")
        notificationLayout.setTextViewText(R.id.content, "this is a content")
        notificationLayoutExpanded.setTextViewText(R.id.title, "text title")
        notificationLayoutExpanded.setTextViewText(R.id.content, "this is a content")

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.notification_icon)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setAutoCancel(true)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)


        if (hasDefaultTitle) {
            builder.setStyle(NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(notificationLayout)
                .setCustomBigContentView(notificationLayoutExpanded)
        } else {
            builder.setCustomContentView(notificationLayout)
                .setCustomBigContentView(notificationLayoutExpanded)
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        builder.setContentIntent(pendingIntent)
        builder.setCategory(NotificationCompat.CATEGORY_ALARM)

        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, builder.build())
        }
    }


}