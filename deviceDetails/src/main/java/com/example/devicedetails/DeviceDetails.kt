package com.example.devicedetails

import android.content.Context
import android.content.Context.TELEPHONY_SERVICE
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

object DeviceDetails {
    fun getDeviceId(c: Context): String? {
        val deviceId: String
        deviceId = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Settings.Secure.getString(c.contentResolver, Settings.Secure.ANDROID_ID)
        } else {
            val mTelephony = c.getSystemService(TELEPHONY_SERVICE) as TelephonyManager
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
                Settings.Secure.getString(c.contentResolver, Settings.Secure.ANDROID_ID)
            }
        }
//        val imeiTextView = findViewById<TextView>(R.id.idIMEI)
//        Toast.makeText(c, deviceId, Toast.LENGTH_SHORT).show()
//        imeiTextView.text = "IMEI ID - "+deviceId
//        Log.d("deviceId", deviceId)
        return deviceId
    }
}