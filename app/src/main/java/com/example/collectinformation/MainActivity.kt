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

// 编写DeviceInfoCollector是一个用于收集各类信息的工具类
object DeviceInfoCollector {
    // getSystemInfo函数用于获取系统信息
    fun getSystemInfo(context: Context): String {
        val builder = StringBuilder()
        builder.append("Device Manufacturer: ").append(Build.MANUFACTURER).append("\n")
        builder.append("Device Model: ").append(Build.MODEL).append("\n")
        builder.append("Android Version: ").append(Build.VERSION.RELEASE).append("\n")
        builder.append("SDK Version: ").append(Build.VERSION.SDK_INT).append("\n")
        builder.append("Android ID: ")
            .append(Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID))
            .append("\n")
        return builder.toString()
    }

    // getSystemInfo函数用于获取用户信息
    fun getUserInfo(context: Context): String {
        val builder = StringBuilder()

        // 获取最后已知的位置信息（！！但是整个会失败，无法获取！！）
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val locationManager: LocationManager =
                context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (lastLocation != null) {
                builder.append("Last Known Location: ").append(lastLocation.latitude).append(", ")
                    .append(lastLocation.longitude).append("\n")
            }
        }

        // 获取SIM卡状态信息
        val telephonyManager =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        builder.append("SIM State: ").append(telephonyManager.getSimState()).append("\n")
        builder.append("SIM Operator Name: ").append(telephonyManager.networkOperatorName)
            .append("\n")
        return builder.toString()
    }

    // getSystemInfo函数用于获取app信息
    fun getInstalledApps(context: Context): String {
        val builder = StringBuilder()
        val packageManager = context.packageManager
        val installedApps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        builder.append("Number of Installed Apps: ").append(installedApps.size.toString()).append("\n\n")
        for (app in installedApps) {
            builder.append("App Name: ").append(app.loadLabel(packageManager)).append("\n")
            builder.append("Package Name: ").append(app.packageName).append("\n")
            builder.append("Version Name: ").append(getAppVersionName(context, app.packageName))
                .append("\n")
            builder.append("Version Code: ").append(getAppVersionCode(context, app.packageName))
                .append("\n")
            builder.append("\n")
        }
        return builder.toString()

    }

    // getAppVersionName函数用于获取指定应用程序的版本名称
    private fun getAppVersionName(context: Context, packageName: String): String {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(packageName, 0)
            packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            "Unknown"
        }
    }

    // getAppVersionCode函数用于获取指定应用程序的版本号
    private fun getAppVersionCode(context: Context, packageName: String): Int {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(packageName, 0)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) packageInfo.longVersionCode
                .toInt() else packageInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            -1
        }
    }
}