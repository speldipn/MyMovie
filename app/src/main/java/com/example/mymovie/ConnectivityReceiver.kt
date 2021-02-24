package com.example.mymovie

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ConnectivityReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val manager = context.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            val activeNetwork = manager.activeNetwork
            val networkCapabilities = manager.getNetworkCapabilities(activeNetwork)

            if(networkCapabilities != null &&
                        networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                connect(context)
            } else {
                notConnected(context)
            }
        }
    }

    private fun connect(context: Context) {
        showToast(context, "connected")
    }

    private fun notConnected(context: Context) {
        showToast(context, "notConnected")
    }

    private fun showToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}