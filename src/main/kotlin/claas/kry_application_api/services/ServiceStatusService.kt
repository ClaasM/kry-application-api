package claas.kry_application_api.services

import claas.kry_application_api.common.updateWhere
import claas.kry_application_api.models.ServiceStatus


class ServiceStatusService() {

    suspend fun find(/*params: Map<String, String>?*/): List<ServiceStatus>? {
        return ServiceStatus.readServices()
    }

    suspend fun get(id: String/*, params: Map<String, String>?*/): ServiceStatus? {
        return ServiceStatus.readServices().find { it.id == id }
    }

    suspend fun create(service: ServiceStatus): ServiceStatus? {
        val services = ServiceStatus.readServices()
        services.add(service)
        println(services.toString())
        ServiceStatus.writeServices(services)
        return service
    }

    suspend fun update(id: String, data: ServiceStatus): ServiceStatus? {
        val service = ServiceStatus(id = id, status = data.status, serviceURL = data.serviceURL)
        val services = ServiceStatus.readServices()
        services.updateWhere({ it.id == id }, service)
        ServiceStatus.writeServices(services)
        return service
    }

    suspend fun remove(id: String): ServiceStatus? {
        val services = ServiceStatus.readServices()
        val service = services.find { it.id == id }
        services.remove(service)
        ServiceStatus.writeServices(services)
        return service
    }
}