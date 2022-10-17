package com.tjohnn.callmonitor.ui.home.mapper

import com.tjohnn.callmonitor.ui.home.model.ServerRunningStateUiModel

class ServerRunningStatePresentationToUiMapper {
    fun toUi(input: Boolean): ServerRunningStateUiModel {
        return if (input) {
            ServerRunningStateUiModel.Running
        } else {
            ServerRunningStateUiModel.NotRunning
        }
    }
}
