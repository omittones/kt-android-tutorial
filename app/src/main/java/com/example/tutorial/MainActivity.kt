package com.example.tutorial

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

const val EXTRA_MESSAGE = "extra.message.key"

class MainActivity : AppCompatActivity() {

    private val sendButton: Button
    get() {
        return this.findViewById(R.id.sendNotification)
    }

    private val notificationText: String
    get() {
        return this.findViewById<EditText>(R.id.notificationText).text.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.sendButton.setOnClickListener(this::sendMessage)
    }

    private fun sendMessage(view: View) {
        val message = this.notificationText
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }
}