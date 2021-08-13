package api

import dev.fritz2.remote.http

class Network {
    val orseApi = http("http://127.0.0.1:8080")
        .acceptJson()
        .contentType("application/json")

}