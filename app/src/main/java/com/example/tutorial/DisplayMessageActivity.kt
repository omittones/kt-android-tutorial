package com.example.tutorial

import com.example.tutorial.keys.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class DisplayMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        val message = intent.getStringExtra(EXTRA_MESSAGE)

        this.findViewById<TextView>(R.id.messageText).apply {
            text = message
        }
    }
}