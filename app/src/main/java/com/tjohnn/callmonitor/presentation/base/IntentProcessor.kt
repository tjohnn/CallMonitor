package com.tjohnn.callmonitor.presentation.base

import kotlinx.coroutines.flow.Flow

interface IntentProcessor<in I : ViewIntent, out R : ViewResult> {
    suspend fun intentToResult(intent: I): Flow<R>
}
