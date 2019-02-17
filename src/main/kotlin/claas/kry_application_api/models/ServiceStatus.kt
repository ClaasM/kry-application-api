package claas.kry_application_api.models

import claas.kry_application_api.common.*
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
            val stream = FileInputStream(DATABASE)
            val reader = InputStreamReader(stream)
            val text = reader.readText()
            val services = this.gson.fromJson(text, Array<ServiceStatus>::class.java)
            reader.close()
            return services.toMutableList()
        }

        fun writeServices(services: MutableList<ServiceStatus>) {
            val stream = FileOutputStream(DATABASE)
            val writer = OutputStreamWriter(stream)
            val text = this.gson.toJson(services.toTypedArray(), Array<ServiceStatus>::class.java)
            writer.write(text)
            writer.close()
        }
    }
}
