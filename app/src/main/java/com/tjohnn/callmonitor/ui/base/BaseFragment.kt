package com.tjohnn.callmonitor.ui.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.tjohnn.callmonitor.presentation.base.BaseViewModel
import com.tjohnn.callmonitor.presentation.base.ViewIntent
import com.tjohnn.callmonitor.presentation.base.ViewResult
import com.tjohnn.callmonitor.presentation.base.ViewState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class BaseFragment<I : ViewIntent, R : ViewResult, S : ViewState> : Fragment() {
    abstract val viewModel: BaseViewModel<I, R, S>
    abstract fun renderViewState(state: S)

    protected fun subscribeToViewModel() {
        super.onStart()
        viewModel.viewState.onEach { renderViewState(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    protected fun renderError(error: Throwable?) {
        error?.let {
            Snackbar.make(
                requireView(),
                com.tjohnn.callmonitor.R.string.generic_error_message,
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}
