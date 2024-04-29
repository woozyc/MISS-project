package com.example.collectinformation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import androidx.core.app.ActivityCompat

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

    // getUserInfo函数用于获取用户信息
    fun getUserInfo(context: Context): String {
        val builder = StringBuilder()
        // 获取SIM卡状态信息
        val telephonyManager =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        builder.append("SIM State: ").append(telephonyManager.getSimState()).append("\n")
        builder.append("SIM Operator Name: ").append(telephonyManager.networkOperatorName)
            .append("\n")
        return builder.toString()
    }

    // getInstalledApps函数用于获取app信息
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