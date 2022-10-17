package com.tjohnn.callmonitor.ui.calllogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tjohnn.callmonitor.R
import com.tjohnn.callmonitor.presentation.calllogs.CallLogsViewIntent
import com.tjohnn.callmonitor.presentation.calllogs.CallLogsViewModel
import com.tjohnn.callmonitor.presentation.calllogs.CallLogsViewResult
import com.tjohnn.callmonitor.presentation.calllogs.CallLogsViewState
import com.tjohnn.callmonitor.ui.UpNavigationCallBack
import com.tjohnn.callmonitor.ui.base.BaseFragment
import com.tjohnn.callmonitor.ui.calllogs.mapper.CallLogUiMapper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CallLogsFragment : BaseFragment<CallLogsViewIntent, CallLogsViewResult, CallLogsViewState>() {

    private val callLogsRecyclerView: RecyclerView get() = requireView().findViewById(R.id.call_logs_recycler_view)

    @Inject
    lateinit var callLogUiMapper: CallLogUiMapper

    @Inject
    lateinit var callLogsAdapter: CallLogsAdapter

    override val viewModel by viewModels<CallLogsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_call_logs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().apply {
            setTitle(R.string.call_logs_title)
            (this as? UpNavigationCallBack)?.enableUpNavigation(true)
        }
        viewModel.processIntent(CallLogsViewIntent.OnViewCreated)
        callLogsRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = callLogsAdapter
        }
        subscribeToViewModel()
    }

    override fun renderViewState(state: CallLogsViewState) {
        val log = state.callLogs.map(callLogUiMapper::map)
        callLogsAdapter.updateItems(log)
    }
}
