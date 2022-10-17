package com.tjohnn.callmonitor.presentation.base

interface ViewStateReducer<R : ViewResult, S : ViewState> {
    fun reduce(previousState: S, result: R): S
}
