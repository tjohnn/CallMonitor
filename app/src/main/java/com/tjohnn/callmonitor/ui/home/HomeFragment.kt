package com.tjohnn.callmonitor.ui.home

import android.Manifest
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tjohnn.callmonitor.R
import com.tjohnn.callmonitor.presentation.home.HomeViewEffect
import com.tjohnn.callmonitor.presentation.home.HomeViewIntent
import com.tjohnn.callmonitor.presentation.home.HomeViewModel
import com.tjohnn.callmonitor.presentation.home.HomeViewResult
import com.tjohnn.callmonitor.presentation.home.HomeViewState
import com.tjohnn.callmonitor.server.CallLogsServerService
import com.tjohnn.callmonitor.ui.UpNavigationCallBack
import com.tjohnn.callmonitor.ui.base.BaseFragment
import com.tjohnn.callmonitor.ui.home.mapper.ServerRunningStatePresentationToUiMapper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewIntent, HomeViewResult, HomeViewState>() {

    private val startServerButton: MaterialButton get() = requireView().findViewById(R.id.start_server_button)
    private val ipAddressText: TextView get() = requireView().findViewById(R.id.ipaddress_text)
    private val portText: TextView get() = requireView().findViewById(R.id.port_text)
    private val viewCallLogsButton: TextView get() = requireView().findViewById(R.id.view_call_logs_button)

    @Inject
    lateinit var serverRunningStatePresentationToUiMapper: ServerRunningStatePresentationToUiMapper

    override val viewModel by viewModels<HomeViewModel>()

    private val callPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { granted ->
            when {
                granted.all { it.value } -> {
                    CallLogsServerService.startSelf(requireContext())
                    startServerButton.isEnabled = false
                }
                else -> showPermissionDeniedDialog()
            }
        }

    private fun showPermissionDeniedDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(R.string.permission_error_message)
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().apply {
            setTitle(R.string.call_monitor_server_title)
            (this as? UpNavigationCallBack)?.enableUpNavigation(false)
        }
        viewModel.processIntent(HomeViewIntent.OnViewCreated)
        viewCallLogsButton.setOnClickListener {
            viewModel.processIntent(HomeViewIntent.OnShowCallLogs)
        }
        subscribeToViewModel()
    }

    override fun renderViewState(state: HomeViewState) {
        ipAddressText.text = state.ipAddress
        portText.text = state.port
        startServerButton.isEnabled = state.isLoadingData.not() && state.error == null
        val serverState = serverRunningStatePresentationToUiMapper.toUi(state.isServerRunning)
        startServerButton.text = getString(serverState.buttonLabelResource)
        startServerButton.backgroundTintList =
            ColorStateList.valueOf(requireContext().getColor(serverState.buttonColorResource))
        setStartServerListener(state.isServerRunning)
        state.viewEffect?.let(::renderViewEffect)
        renderError(state.error)
    }

    private fun setStartServerListener(isServerRunning: Boolean) {
        startServerButton.setOnClickListener {
            if (isServerRunning) {
                viewModel.processIntent(HomeViewIntent.OnStopServer)
            } else {
                viewModel.processIntent(HomeViewIntent.OnStartServer)
            }
        }
    }

    private fun renderViewEffect(effect: HomeViewEffect) {
        when (effect) {
            HomeViewEffect.NavigateToCallLogs -> {
                findNavController().navigate(R.id.action_homeFragment_to_callLogsFragment)
            }
            HomeViewEffect.StartServer -> {
                val permissions = arrayOf(
                    Manifest.permission.READ_CALL_LOG,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_CONTACTS
                )
                callPermission.launch(permissions)
            }
            HomeViewEffect.StopServer -> CallLogsServerService.stopSelf(requireContext())
        }
    }
}
