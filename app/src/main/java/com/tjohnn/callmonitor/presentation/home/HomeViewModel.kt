package com.tjohnn.callmonitor.presentation.home

import com.tjohnn.callmonitor.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    homeIntentProcessor: HomeIntentProcessor,
    homeViewStateReducer: HomeViewStateReducer
) : BaseViewModel<HomeViewIntent, HomeViewResult, HomeViewState>(
    homeIntentProcessor,
    homeViewStateReducer
) {
    override fun initialState() = HomeViewState()
}
