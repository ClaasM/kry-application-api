package claas.kry_application_api.handlers

import claas.kry_application_api.models.SimpleMessage
import claas.kry_application_api.services.HomeJsonService
import claas.kry_application_api.common.endWithJson
import io.vertx.ext.web.RoutingContext
import org.slf4j.LoggerFactory
import claas.kry_application_api.common.safeLaunch

class SimpleHandlers() {
    private val logger = LoggerFactory.getLogger("VertxServer")

    suspend fun homeJsonHandler(ctx: RoutingContext) {
        safeLaunch(ctx) {
            val homeJsonInfo = HomeJsonService().getResult()

            ctx.response().endWithJson(homeJsonInfo)
        }
    }

    suspend fun helloJsonHandler(ctx: RoutingContext) {
        safeLaunch(ctx) {
            ctx.response().endWithJson(SimpleMessage("Hello, World!"))
        }
    }
}