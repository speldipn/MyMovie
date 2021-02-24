package com.example.mymovie

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_my_noti.*

const val channelId = "noti"
const val channelName = "noti"
const val description = "noti test"

class MyNotiActivity : AppCompatActivity() {

    private var notiManager: NotificationManagerCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_noti)
        setup()
    }

    private fun setup() {
        // 1. Notification Manager
        notiManager = NotificationManagerCompat.from(applicationContext)
        createNotificationChannel(channelId, channelName, description)

        sendNotiButton.setOnClickListener {
            val resultIntent = Intent(applicationContext, MainActivity::class.java)
            showNotification("Test", "Content test", resultIntent)
        }
    }

    private fun showNotification(title: String, content: String, resultIntent: Intent) {
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, resultIntent, 0)

        val notificaiton = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setChannelId(channelId)
            .setContentIntent(pendingIntent)
            .build()

        notificaiton.flags = Notification.FLAG_NO_CLEAR

        notiManager?.notify(101, notificaiton)
    }

    private fun createNotificationChannel(
        channelId: String,
        channelName: String,
        description: String
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_LOW
            // 2. Create notification channel
            val channel = NotificationChannel(channelId, channelName, importance)

            channel.description = description
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)

            // 3. Make channel by notification manager
            notiManager?.createNotificationChannel(channel)
        }
    }
}