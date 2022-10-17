package com.tjohnn.callmonitor.dispatcher

import com.tjohnn.callmonitor.domain.dispatcher.Dispatcher
import kotlinx.coroutines.test.StandardTestDispatcher

val testDispatcher = TestDispatcher()

class TestDispatcher : Dispatcher {

    override fun io() = StandardTestDispatcher()

    override fun main() = StandardTestDispatcher()
}
