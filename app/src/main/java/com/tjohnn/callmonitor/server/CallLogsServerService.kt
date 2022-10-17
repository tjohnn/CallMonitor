package com.tjohnn.callmonitor.server

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.provider.ContactsContract
import android.provider.ContactsContract.PhoneLookup
import android.telephony.TelephonyManager
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.tjohnn.callmonitor.R
import com.tjohnn.callmonitor.call.CallBroadcastReceiver
import com.tjohnn.callmonitor.call.CallStateChangeCallback
import com.tjohnn.callmonitor.data.server.LocalIpAddressFacade
import com.tjohnn.callmonitor.domain.dispatcher.Dispatcher
import com.tjohnn.callmonitor.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.ServerSocket
import javax.inject.Inject

private const val FOREGROUND_NOTIFICATION_ID = 1
private const val FOREGROUND_NOTIFICATION_CHANNEL = "server"
const val SERVER_PORT = 8080

@AndroidEntryPoint
class CallLogsServerService : Service() {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    @Inject
    lateinit var callLogsController: CallLogsController

    @Inject
    lateinit var apiController: ApiController

    @Inject
    lateinit var localIpAddressFacade: LocalIpAddressFacade

    @Inject
    lateinit var dispatcher: Dispatcher

    val server by lazy { embeddedServer(CIO, SERVER_PORT) { setupServer(apiController) } }

    private val callBroadcastReceiver by lazy {
        val callback = object : CallStateChangeCallback {
            override fun onCallStarted(phoneNumber: String) {
                coroutineScope.launch {
                    callLogsController.registerCallStarted(phoneNumber, getCallerName(phoneNumber))
                }
            }

            override fun onCallEnded(phoneNumber: String) {
                coroutineScope.launch { callLogsController.registerCallEnded(phoneNumber) }
            }
        }
        CallBroadcastReceiver(callback)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        startForegroundNotification()
        registerCallBroadcastReceiver()
        startServer()
        return START_STICKY
    }

    private fun startServer() {
        coroutineScope.launch {
            withContext(dispatcher.io()) {
                if (isPortAvailable()) {
                    server.start(true)
                } else {
                    handlePortNotAvailable()
                }
            }
        }
        coroutineScope.launch { callLogsController.setServiceStarted() }
    }

    private suspend fun handlePortNotAvailable() {
        withContext(dispatcher.main()) {
            Toast.makeText(
                this@CallLogsServerService,
                getString(R.string.unavailable_port_message, SERVER_PORT),
                Toast.LENGTH_SHORT
            ).show()
            stopSelf()
        }
    }

    private fun isPortAvailable(): Boolean {
        return try {
            val serverSocket = ServerSocket(SERVER_PORT)
            serverSocket.reuseAddress = true
            serverSocket.close()
            true
        } catch (e: IOException) {
            false
        }
    }

    override fun onDestroy() {
        coroutineScope.launch {
            server.stop()
            callLogsController.setServiceStopped()
            coroutineScope.coroutineContext.cancel()
        }
        unregisterReceiver(callBroadcastReceiver)
        super.onDestroy()
    }

    private fun registerCallBroadcastReceiver() {
        val filterCalls = IntentFilter()
        filterCalls.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED)
        registerReceiver(callBroadcastReceiver, filterCalls)
    }

    private fun startForegroundNotification() {
        val mainActivityIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            mainActivityIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val builder = NotificationCompat.Builder(this, createForeGroundNotificationChannel(this))
            .setTicker(getString(R.string.foreground_notification_title))
            .setContentTitle(getString(R.string.foreground_notification_title))
            .setContentText(
                getString(
                    R.string.foreground_notification_subtitle,
                    localIpAddressFacade.getSystemLocalIpAddress()
                )
            )
            .setContentIntent(pendingIntent)
            .setDefaults(NotificationCompat.DEFAULT_SOUND and NotificationCompat.DEFAULT_VIBRATE)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setOngoing(true)
            .setOnlyAlertOnce(true)
        startForeground(FOREGROUND_NOTIFICATION_ID, builder.build())
    }

    private fun createForeGroundNotificationChannel(context: Context): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notificationChannel = NotificationChannel(
                FOREGROUND_NOTIFICATION_CHANNEL,
                FOREGROUND_NOTIFICATION_CHANNEL,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(false)
            notificationChannel.setShowBadge(false)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        return FOREGROUND_NOTIFICATION_CHANNEL
    }

    private fun getCallerName(phoneNumber: String): String {
        if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            return ""
        }
        val uri: Uri = ContactsContract.Data.CONTENT_URI
        val projection = arrayOf(PhoneLookup._ID, PhoneLookup.DISPLAY_NAME)
        val selection = ContactsContract.CommonDataKinds.Phone.NUMBER + " = ?"
        val selectionArgs = arrayOf(phoneNumber)
        val cursor: Cursor? = contentResolver.query(uri, projection, selection, selectionArgs, null)
        val displayNameColumnIndex = cursor?.getColumnIndex(PhoneLookup.DISPLAY_NAME) ?: -1

        return if (cursor != null && cursor.moveToFirst() && displayNameColumnIndex >= 0) {
            val contactName = cursor.getString(displayNameColumnIndex)
            cursor.close()
            contactName
        } else {
            ""
        }
    }

    companion object {
        fun startSelf(context: Context) {
            val intent = Intent(context, CallLogsServerService::class.java)
            ContextCompat.startForegroundService(context, intent)
        }

        fun stopSelf(context: Context) {
            context.stopService(Intent(context, CallLogsServerService::class.java))
        }
    }
}
