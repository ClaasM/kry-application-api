package claas.kry_application_api.routes

import claas.kry_application_api.common.coroutineHandler
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import claas.kry_application_api.common.API_ENDPOINT
import claas.kry_application_api.handlers.ConfigHandlers
import claas.kry_application_api.handlers.ServiceStatusHandlers

class Routes(val vertx: Vertx) {
    fun createRouter(): Router {
        val ConfigHandlers = ConfigHandlers()
        val ServiceStatusHandlers = ServiceStatusHandlers()

        return Router.router(vertx).apply {
            // CONFIG ROUTES
            route().handler(ConfigHandlers.corsHandler)
            route().handler(ConfigHandlers.bodyHandler)

            // SERVICES ROUTES
            get   ("$API_ENDPOINT/services")    .coroutineHandler { ServiceStatusHandlers.getServices(it) }
            get   ("$API_ENDPOINT/services/:id").coroutineHandler { ServiceStatusHandlers.getServiceById(it) }
            post  ("$API_ENDPOINT/services")    .coroutineHandler { ServiceStatusHandlers.addService(it) }
            put   ("$API_ENDPOINT/services/:id").coroutineHandler { ServiceStatusHandlers.updateServiceById(it) }
            delete("$API_ENDPOINT/services/:id").coroutineHandler { ServiceStatusHandlers.removeServiceById(it) }

            // route("/public/*").handler(ConfigHandlers.staticHandler)
            route().handler { ConfigHandlers.otherPageHandler(it) }
        }
    }
}