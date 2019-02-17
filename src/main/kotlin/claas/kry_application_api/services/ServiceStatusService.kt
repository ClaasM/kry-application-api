package claas.kry_application_api.services

import claas.kry_application_api.common.STORAGE_FILE
import claas.kry_application_api.common.updateWhere
import claas.kry_application_api.models.ServiceStatus
import claas.kry_application_api.models.ServiceStatusAdapter
import com.beust.klaxon.*
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.vertx.core.Vertx
import io.vertx.core.file.OpenOptions
import io.vertx.core.parsetools.JsonParser
import java.util.*
import java.io.FileInputStream
import java.io.InputStreamReader


class ServiceStatusService() {

    private val services: MutableList<ServiceStatus> = mutableListOf<ServiceStatus>()
    private var gson: Gson

    init {
        val gsonBuilder = GsonBuilder()
        GsonBuilder().registerTypeAdapter(ServiceStatus::class.java, ServiceStatusAdapter())
        this.gson = gsonBuilder.create()
    }


    suspend fun find(/*params: Map<String, String>?*/): List<ServiceStatus>? {
        val stream = FileInputStream(STORAGE_FILE)
        val reader = InputStreamReader(stream)
        val text = reader.readText()
        val status = gson.fromJson(text, Array<ServiceStatus>::class.java)
        return status.asList()
    }

    suspend fun get(id: UUID/*, params: Map<String, String>?*/): ServiceStatus? {
        return services.find { it.id == id }
    }

    suspend fun create(data: ServiceStatus): ServiceStatus? {
        services.add(data)

        return data
    }

    suspend fun update(id: UUID, data: ServiceStatus): ServiceStatus? {
        val service = ServiceStatus(id = id, status = data.status, serviceURL = data.serviceURL)
        services.updateWhere({ it.id == id }, service)

        return service
    }

    suspend fun remove(id: UUID): ServiceStatus? {
        val service = services.find { it.id == id }
        services.remove(service)

        return service
    }
}