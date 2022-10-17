package com.tjohnn.callmonitor.call

interface CallStateChangeCallback {
    fun onCallStarted(phoneNumber: String)
    fun onCallEnded(phoneNumber: String)
}
