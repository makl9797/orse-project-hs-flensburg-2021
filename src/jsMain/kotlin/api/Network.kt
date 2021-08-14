package api

import dev.fritz2.remote.http

class Network {
    val orseApi = http("http://localhost:8080")
        .acceptJson()
        .contentType("application/json")

}