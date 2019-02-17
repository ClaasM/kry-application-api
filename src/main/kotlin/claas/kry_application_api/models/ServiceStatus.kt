package claas.kry_application_api.models

import claas.kry_application_api.common.HOST
import claas.kry_application_api.common.PORT
import com.google.gson.*
import java.lang.reflect.Type
import java.net.URL
import java.util.*

enum class Status {
    UP, DOWN, NOT_CRAWLED
}

class ServiceStatus(
        // Whats the best way to parse URIs in Vert.x/Kotlin?
        val serviceURL: String,
        var id: UUID = UUID.randomUUID(),
        var status: Status = Status.NOT_CRAWLED) {

    var url: String = ""
        get() {
            return "http://$HOST:$PORT/api/services/${id}"
        }


}

class ServiceStatusAdapter : JsonDeserializer<Any>, JsonSerializer<Any> {
    @Throws(JsonParseException::class)
    override fun deserialize(jsonElement: JsonElement,
                             type: Type,
                             jsonDeserializationContext: JsonDeserializationContext): Any {

        val jsonObject = jsonElement.asJsonObject
        println(jsonObject.toString())
        val serviceStatus = ServiceStatus(serviceURL = (jsonObject.get("serviceURL").asString))

        if (jsonObject.has("id"))
            serviceStatus.id = UUID.fromString(jsonObject.get("id").asString)
        if (jsonObject.has("status"))
            serviceStatus.status = Status.values()[jsonObject.get("status").asInt]

        return serviceStatus
    }

    override fun serialize(jsonElement: Any,
                           type: Type,
                           jsonSerializationContext: JsonSerializationContext): JsonElement {

        val jsonObject = JsonObject()
        val serviceStatus = jsonElement as ServiceStatus
        jsonObject.addProperty("serviceURL", serviceStatus.serviceURL)
        jsonObject.addProperty("id", serviceStatus.id.toString())
        jsonObject.addProperty("status", serviceStatus.status.ordinal)
        return jsonObject
    }
}
