package com.tjohnn.callmonitor.data.cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class Cache<Model> {
    private val _flow = MutableSharedFlow<Model>(replay = 1)
    val flow: Flow<Model> get() = _flow
    private val mutex = Mutex()

    suspend fun save(value: Model) {
        mutex.withLock {
            _flow.emit(value)
        }
    }

    suspend fun saveIfEmpty(value: Model) {
        mutex.withLock {
            if (_flow.replayCache.isEmpty()) {
                _flow.emit(value)
            }
        }
    }
}
