package com.example.collectinformation

import android.os.Bundle
import com.example.collectinformation.util.SystemUtils
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SensorActivity : AppCompatActivity(), SensorEventListener {
    private var ms_x: TextView? = null
    private var ms_y: TextView? = null
    private var ms_z: TextView? = null
    private var ms_sensor_info: TextView? = null
    private var sm: SensorManager? = null
    private var mSensorOrientation: Sensor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)

        //获取传感器管理器
        sm = getSystemService(SENSOR_SERVICE) as SensorManager

        // 获取加速度传感器
        mSensorOrientation = sm!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        //注册数值变化监听器
        sm!!.registerListener(this, mSensorOrientation, SensorManager.SENSOR_DELAY_UI)
        ms_x = findViewById<View>(R.id.ms_x) as TextView
        ms_y = findViewById<View>(R.id.ms_y) as TextView
        ms_z = findViewById<View>(R.id.ms_z) as TextView

        ms_sensor_info = findViewById<View>(R.id.sensorText) as TextView
        val sensor = SystemUtils.showSensorInfo(this)
        ms_sensor_info!!.text = sensor
    }

    // 传感器数值变化会调用此方法
    override fun onSensorChanged(event: SensorEvent) {
        ms_x!!.text = "X：" + Math.round(event.values[0] * 100).toFloat() / 100
        ms_y!!.text = "Y：" + Math.round(event.values[1] * 100).toFloat() / 100
        ms_z!!.text = "Z：" + Math.round(event.values[2] * 100).toFloat() / 100
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
}