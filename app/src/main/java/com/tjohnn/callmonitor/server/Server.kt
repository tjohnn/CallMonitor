package com.tjohnn.callmonitor.server

import com.tjohnn.callmonitor.server.model.ErrorResponse
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.setupServer(controller: ApiController) {
    install(ContentNegotiation) { json() }
    install(StatusPages) {
        exception<Throwable> { call, _ ->
            call.respond(
                HttpStatusCode.InternalServerError,
                ErrorResponse("Something went wrong")
            )
        }
    }
    routing {
        val routing = this
        get("/") {
            val routes = routing.children.map { it.toString() }
            call.respond(controller.getServiceInformation(routes))
        }
        get("status") {
            call.respond(controller.getOngoingCallStatus())
        }
        get("log") {
            call.respond(controller.getCallLogs())
        }
    }
}
