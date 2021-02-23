package com.example.mymovie

import android.content.Intent
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }

    private fun setup() {
        startServiceButton.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            startService(intent)
        }

        sendButton.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            intent.putExtra("username", "Neo")
            startService(intent)
        }
    }

    private fun debug(msg: String) = Log.d(TAG, msg)
}