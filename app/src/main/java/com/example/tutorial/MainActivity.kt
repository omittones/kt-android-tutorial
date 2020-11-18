package com.example.tutorial

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.*
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorial.keys.*
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity() : AppCompatActivity(), SensorEventListener {

    private var sensorValues: String = ""
    private var timestamp: Long = 0
    private lateinit var timer: Timer
    private lateinit var manager: SensorManager
    private lateinit var sensor: Sensor

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

        manager = this.getSystemService(SensorManager::class.java)
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        this.sendButton.setOnClickListener(this::sendMessage)
    }

    private fun startTimer() {
        this.timer = Timer()
        val activity = this
        val logView = activity.findViewById<TextView>(R.id.logView)
        val task = object : TimerTask() {
            @SuppressLint("SetTextI18n")
            override fun run() {
                activity.runOnUiThread {
                    logView.text = "${activity.timestamp} : ${activity.sensorValues}"
                }
            }
        }
        this.timer.schedule(task, 0, 100)
    }

    override fun onStart() {
        super.onStart()
        manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST)
        startTimer()
    }

    override fun onResume() {
        super.onResume()
        manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST)
        startTimer()
    }

    override fun onPause() {
        super.onPause()
        manager.unregisterListener(this)
        this.timer.cancel()
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        this.sensorValues = event!!.values.joinToString(transform = Float::toString)
        this.timestamp = event.timestamp
        //Log.i(null, "${event.timestamp} : ${this.sensorValues}")
    }

    private fun sendMessage(view: View) {
        val message = this.notificationText
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }
}