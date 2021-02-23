package com.example.mymovie

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    private val TAG = "speldipn"

    override fun onCreate() {
        super.onCreate()
        debug("onCreate called")
    }

    override fun onDestroy() {
        super.onDestroy()
        debug("onDestroy called")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        debug("onStartCommand called")

        intent?.apply { processIntent(intent) } ?: return START_STICKY

        return super.onStartCommand(intent, flags, startId)
    }

    private fun processIntent(intent: Intent) {
        intent.extras?.apply {
            val username = this.getString("username")
            username?.let { debug(it) }
        }
    }

    private fun debug(msg: String) = Log.d(TAG, msg)

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}