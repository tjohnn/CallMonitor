package com.tjohnn.callmonitor.presentation.usecase

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.whenever
import com.tjohnn.callmonitor.domain.base.BaseUseCase
import kotlinx.coroutines.runBlocking

suspend fun <Input : Any, Output : Any> BaseUseCase<Input, Output>.givenSuccessfulExecution(
    input: Input,
    output: Output
) {
    doAnswer { invocationOnMock ->
        runBlocking { (invocationOnMock.arguments[1] as suspend (Output) -> Unit)(output) }
    }.whenever(this).executeInBackground(
        input = eq(input),
        callback = any(),
        onError = any()
    )
}

suspend fun <Input : Any, Output : Any> BaseUseCase<Input, Output>.givenFailedExecution(
    input: Input,
    error: Throwable
) {
    doAnswer { invocationOnMock ->
        runBlocking { (invocationOnMock.arguments[2] as suspend (Throwable) -> Unit)(error) }
    }.whenever(this).executeInBackground(
        input = eq(input),
        callback = any(),
        onError = any()
    )
}
