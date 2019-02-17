package claas.kry_application_api.handlers

import claas.kry_application_api.common.endWithJson
import claas.kry_application_api.models.ServiceStatus
import claas.kry_application_api.services.ServiceStatusService
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import io.vertx.ext.web.RoutingContext
import claas.kry_application_api.common.safeLaunch
import java.util.*

class ServiceStatusHandlers() {
    private val ServiceStatusService = ServiceStatusService()

    suspend fun getServices(ctx: RoutingContext) {
        safeLaunch(ctx) {
            val services = ServiceStatusService.find()
            ctx.response().endWithJson(services)
        }
    }

    suspend fun getServiceById(ctx: RoutingContext) {
        safeLaunch(ctx) {
            val _id = UUID.fromString(ctx.request().getParam("id"))
            val _service = ServiceStatusService.get(_id)
            ctx.response().endWithJson(_service)
        }
    }

    suspend fun addService(ctx: RoutingContext) {
        safeLaunch(ctx) {
            val tmpService: ServiceStatus = ServiceStatus.gson.fromJson(ctx.bodyAsString)
            val newService: ServiceStatus = ServiceStatus(serviceURL = tmpService.serviceURL)
            val _service = ServiceStatusService.create(newService)
            ctx.response().endWithJson(_service)
        }
    }

    suspend fun updateServiceById(ctx: RoutingContext) {
        safeLaunch(ctx) {
            val _id = UUID.fromString(ctx.request().getParam("id"))
            val newService: ServiceStatus = gson.fromJson(ctx.bodyAsString)
            val _todo = ServiceStatusService.update(_id, newService)
            ctx.response().endWithJson(_todo)
        }
    }

    suspend fun removeServiceById(ctx: RoutingContext) {
        safeLaunch(ctx) {
            val _id = UUID.fromString(ctx.request().getParam("id"))
            val _service = ServiceStatusService.remove(_id)
            ctx.response().endWithJson(_service)
        }
    }
}