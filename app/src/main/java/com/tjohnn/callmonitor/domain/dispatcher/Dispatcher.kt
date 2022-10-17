package com.tjohnn.callmonitor.domain.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface Dispatcher {
    fun io(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
}
