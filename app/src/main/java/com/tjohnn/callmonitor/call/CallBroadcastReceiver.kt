package com.tjohnn.callmonitor.call

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager

private const val CALL_STATE_IDLE: String = "IDLE"
private const val CALL_STATE_OFFHOOK: String = "OFFHOOK"

class CallBroadcastReceiver(
    private val callback: CallStateChangeCallback
) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            val phoneNumber = intent.extras?.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)
            val state = intent.extras?.getString(TelephonyManager.EXTRA_STATE)
            if (!phoneNumber.isNullOrBlank() && !state.isNullOrBlank()) {
                sendCallState(state, phoneNumber)
            }
        }
    }

    private fun sendCallState(state: String, phoneNumber: String) {
        when (state) {
            CALL_STATE_IDLE -> callback.onCallEnded(phoneNumber)
            CALL_STATE_OFFHOOK -> callback.onCallStarted(phoneNumber)
        }
    }
}
