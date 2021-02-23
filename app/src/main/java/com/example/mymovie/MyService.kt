package com.example.mymovie

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    private val TAG = "speldipn"

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        debug("onDestroy called")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.apply { processIntent(intent) } ?: return START_STICKY

        return super.onStartCommand(intent, flags, startId)
    }

    private fun processIntent(intent: Intent) {
        intent.extras?.apply {
            val username = this.getString("username")

            val showIntent = Intent(applicationContext, MainActivity::class.java)
            showIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK +
                        Intent.FLAG_ACTIVITY_SINGLE_TOP +
                        Intent.FLAG_ACTIVITY_CLEAR_TOP)
            showIntent.putExtra("command", "show")
            showIntent.putExtra("username", username)
            startActivity(showIntent)
        }
    }

    private fun debug(msg: String) = Log.d(TAG, msg)

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}