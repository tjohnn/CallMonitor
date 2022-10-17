package com.tjohnn.callmonitor.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn

abstract class BaseViewModel<I : ViewIntent, R : ViewResult, S : ViewState>(
    private val intentProcessor: IntentProcessor<I, R>,
    private val stateReducer: ViewStateReducer<R, S>
) : ViewModel() {

    protected abstract fun initialState(): S

    private val intents = Channel<I>(onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val _viewState: StateFlow<S> = intents.receiveAsFlow()
        .flatMapMerge(transform = intentProcessor::intentToResult)
        .scan(initialState(), stateReducer::reduce)
        .stateIn(viewModelScope, SharingStarted.Lazily, initialState())
    val viewState: Flow<S> get() = _viewState

    fun processIntent(intent: I) {
        intents.trySend(intent)
    }
}
