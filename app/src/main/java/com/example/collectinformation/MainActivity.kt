package com.example.collectinformation

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import androidx.core.app.ActivityCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        clickListener()
    }

    public fun clickListener(){
        val phoneInfoCardView = findViewById<View>(R.id.cardPhone) as CardView
        phoneInfoCardView.setOnClickListener{
            openPhoneActivity()
        }
        val appInfoCardView = findViewById<View>(R.id.cardApp) as CardView
        appInfoCardView.setOnClickListener{
            openAppActivity()
        }
        val locInfoCardView = findViewById<View>(R.id.cardLoc) as CardView
        locInfoCardView.setOnClickListener{
            openLocActivity()
        }
        val sensorInfoCardView = findViewById<View>(R.id.cardSensor) as CardView
        sensorInfoCardView.setOnClickListener{
            openSensorActivity()
        }
    }

    public fun openPhoneActivity(){
        startActivity(Intent(this@MainActivity, PhoneActivity::class.java))
    }
    public fun openAppActivity(){
        startActivity(Intent(this@MainActivity, AppActivity::class.java))
    }
    public fun openLocActivity(){
        startActivity(Intent(this@MainActivity, LocActivity::class.java))
    }
    public fun openSensorActivity(){
        startActivity(Intent(this@MainActivity, SensorActivity::class.java))
    }
}
