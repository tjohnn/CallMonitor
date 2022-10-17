package com.tjohnn.callmonitor.domain.base

import com.tjohnn.callmonitor.domain.dispatcher.Dispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseUseCase<Input : Any, Output : Any>(private val dispatcher: Dispatcher) {
    suspend fun executeInBackground(
        input: Input,
        callback: suspend (Output) -> Unit = {},
        onError: suspend (Throwable) -> Unit = {}
    ) {
        try {
            execute(input).flowOn(dispatcher.io())
                .collect { callback(it) }
        } catch (error: Throwable) {
            onError(error)
        }
    }

    abstract suspend fun execute(input: Input): Flow<Output>
}
