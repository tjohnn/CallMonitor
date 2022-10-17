package com.tjohnn.callmonitor.data.android

import com.tjohnn.callmonitor.data.server.LocalIpAddressFacade
import timber.log.Timber
import java.net.InetAddress
import java.net.NetworkInterface

class LocalIpAddressFacadeImpl : LocalIpAddressFacade {

    override fun getSystemLocalIpAddress(): String? {
        return getInetAddresses()
            .filter { it.isLocalAddress() }
            .map { it.hostAddress }
            .firstOrNull()
    }

    private fun getInetAddresses(): Collection<InetAddress> {
        return NetworkInterface.getNetworkInterfaces()
            .iterator()
            .asSequence()
            .flatMap { networkInterface ->
                networkInterface.inetAddresses
                    .asSequence()
                    .filter { !it.isLoopbackAddress }
            }.toList()
    }

    private fun InetAddress.isLocalAddress(): Boolean {
        return try {
            isSiteLocalAddress &&
                requireNotNull(hostAddress).contains(":").not() &&
                hostAddress != "127.0.0.1"
        } catch (e: Throwable) {
            Timber.e(e)
            false
        }
    }
}
