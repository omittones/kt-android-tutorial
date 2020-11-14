package com.example.tutorial

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorial.keys.*

class MainActivity() : AppCompatActivity(), SensorEventListener {

    private val sendButton: Button
    get() {
        return this.findViewById(R.id.sendNotification)
    }

    private val notificationText: String
    get() {
        return this.findViewById<EditText>(R.id.notificationText).text.toString()
    }

    private var manager: SensorManager? = null
    private var sensor: Sensor? = null

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = this.getSystemService(SensorManager::class.java)
        sensor = manager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        this.sendButton.setOnClickListener(this::sendMessage)
    }

    override fun onStart() {
        super.onStart()
        manager!!.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST)
    }

    override fun onResume() {
        super.onResume()
        manager!!.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST)
    }

    override fun onPause() {
        super.onPause()
        manager!!.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val representation = event!!.values.joinToString(transform = Float::toString)
        val logView = this.findViewById<TextView>(R.id.logView)
        logView.text = representation
    }

    private fun sendMessage(view: View) {
        val message = this.notificationText
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }
}