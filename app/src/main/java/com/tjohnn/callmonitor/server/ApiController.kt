package com.tjohnn.callmonitor.server

import com.tjohnn.callmonitor.domain.call.GetOngoingCallStatusUseCase
import com.tjohnn.callmonitor.domain.call.QueryCallLogsUseCase
import com.tjohnn.callmonitor.domain.serverinformation.GetServerInformationUseCase
import com.tjohnn.callmonitor.server.mapper.CallLogResponseMapper
import com.tjohnn.callmonitor.server.mapper.OngoingCallResponseMapper
import com.tjohnn.callmonitor.server.model.ApiRoute
import com.tjohnn.callmonitor.server.model.CallLogResponseModel
import com.tjohnn.callmonitor.server.model.OngoingCallStatusResponseModel
import com.tjohnn.callmonitor.server.model.ServerInformationResponseModel
import kotlinx.coroutines.flow.first

class ApiController(
    private val getServerInformationUseCase: GetServerInformationUseCase,
    private val queryCallLogsUseCase: QueryCallLogsUseCase,
    private val getOngoingCallStatusUseCase: GetOngoingCallStatusUseCase,
    private val ongoingCallResponseMapper: OngoingCallResponseMapper,
    private val callLogResponseMapper: CallLogResponseMapper
) {

    suspend fun getServiceInformation(allRoutes: Collection<String>): ServerInformationResponseModel {
        val serverInfo = getServerInformationUseCase.execute(Unit).first()
        val baseUrl = "http://${serverInfo.ipAddress}:${serverInfo.port}"
        val routes = allRoutes.filter { it != "/" }.map { route ->
            ApiRoute(route.split("/").last(), "$baseUrl$route")
        }
        return ServerInformationResponseModel(serverInfo.timeStarted.toString(), routes)
    }

    suspend fun getOngoingCallStatus(): OngoingCallStatusResponseModel {
        val ongoingCall = getOngoingCallStatusUseCase.execute(Unit).first()
        return ongoingCallResponseMapper.map(ongoingCall)
    }

    suspend fun getCallLogs(): List<CallLogResponseModel> {
        val ongoingCall = queryCallLogsUseCase.execute(Unit).first()
        return ongoingCall.map(callLogResponseMapper::map)
    }
}
