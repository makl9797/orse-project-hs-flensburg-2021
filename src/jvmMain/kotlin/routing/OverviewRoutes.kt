package routing

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.overviewRoutes() {
    get ("/overview"){
        try {
            var period = call.parameters["periodInDays"]?.toInt()
            if (period == null) {
                period = 7
            }

            call.respondText { period.toString() }
            call.respond(HttpStatusCode.OK)

        }catch (e: Exception){
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}

