package com.example.mymovie

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_one.*

class MainActivity : AppCompatActivity() {

    val TAG = "speldipn"
    lateinit var receiver: BroadcastReceiver
    lateinit var connectivityReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        configureReceiver()
        configureConnectivityReceiver()
        setup()
    }

    private fun setup() {
        sendButton.setOnClickListener {
            val userName = editText.text.toString()

            val intent = Intent().apply {
                // Sender: action
                action = "com.example.mymovie"
                putExtra("username", userName)
            }
            sendBroadcast(intent)
        }
    }

    private fun configureReceiver() {
        val filter = IntentFilter()
        // Receiver: action
        filter.addAction("com.example.mymovie")
        receiver = MyReceiver()
        registerReceiver(receiver, filter)

    }

    private fun configureConnectivityReceiver() {
        val filter = IntentFilter()
        // TODO: Set action filter
//        filter.addAction(Intent.)
        connectivityReceiver = ConnectivityReceiver()
        registerReceiver(connectivityReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    private fun debug(msg: String) = Log.d(TAG, msg)
}