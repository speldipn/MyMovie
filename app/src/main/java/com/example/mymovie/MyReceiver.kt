package com.example.mymovie

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    val TAG = "spdn"
    lateinit var context: Context

    override fun onReceive(context: Context, intent: Intent) {
        debug("onReceive")
        this.context = context

        val username = intent.extras?.getString("username")
        showToast("$username")
    }

    private fun debug(msg: String) {
        Log.d(TAG, msg)
    }

    private fun showToast(msg: String) {
       Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show()
    }
}