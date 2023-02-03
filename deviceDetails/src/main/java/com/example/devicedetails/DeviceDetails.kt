package com.example.devicedetails

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DeviceDetails : AppCompatActivity() {
    fun getDeviceId(context: Context): String? {
        val deviceId: String
        deviceId = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        } else {
            val mTelephony = context.getSystemService(TELEPHONY_SERVICE) as TelephonyManager
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            }*/
            assert(mTelephony != null)
            if (mTelephony.deviceId != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    mTelephony.imei
                } else {
                    mTelephony.deviceId
                }
            } else {
                Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            }
        }
        val imeiTextView = findViewById<TextView>(R.id.idIMEI)
        imeiTextView.text = "IMEI ID - "+deviceId
        Log.d("deviceId", deviceId)
        return deviceId
    }

}