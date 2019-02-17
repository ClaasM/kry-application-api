package claas.kry_application_api.models

import claas.kry_application_api.common.API_ENDPOINT
import claas.kry_application_api.common.HOST
import claas.kry_application_api.common.PORT
import claas.kry_application_api.common.STORAGE_FILE
import com.google.gson.*
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.reflect.Type
import java.util.*
import javax.xml.ws.Service


class ServiceStatus(
        // Whats the best way to parse URIs in Vert.x/Kotlin?
        val serviceURL: String,
        // There is a Datatype UUID, and I created an GSON adapter for it, but it doesn't make sense if its serialized through the JSON API anyways.
        var id: String = UUID.randomUUID().toString(),
        // Unfortunately, enums are nulled by vert.x
        var status: String = "NOT CRAWLED") {

    var url: String = ""
        get() {
            return "http://$HOST:$PORT/$API_ENDPOINT/services/${id}"
        }


    companion object {

        var gson: Gson = Gson()

        fun readServices(): MutableList<ServiceStatus> {
            val stream = FileInputStream(STORAGE_FILE)
            val reader = InputStreamReader(stream)
            val text = reader.readText()
            val services = this.gson.fromJson(text, Array<ServiceStatus>::class.java)
            reader.close()
            return services.asList() as MutableList
        }

        fun writeServices(services: List<ServiceStatus>) {
            val stream = FileOutputStream(STORAGE_FILE)
            val writer = OutputStreamWriter(stream)
            val text = this.gson.toJson(services, Array<ServiceStatus>::class.java)
            writer.write(text)
            writer.close()
        }
    }
}
