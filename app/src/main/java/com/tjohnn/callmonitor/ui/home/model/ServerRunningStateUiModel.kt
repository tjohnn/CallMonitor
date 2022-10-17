package com.tjohnn.callmonitor.ui.home.model

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.tjohnn.callmonitor.R

sealed class ServerRunningStateUiModel(
    @StringRes val buttonLabelResource: Int,
    @ColorRes val buttonColorResource: Int
) {
    object Running : ServerRunningStateUiModel(
        R.string.stop_server_label,
        R.color.red
    )

    object NotRunning : ServerRunningStateUiModel(
        R.string.start_server_label,
        R.color.teal_700
    )
}
