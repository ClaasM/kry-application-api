package claas.kry_application_api.common

private val DEFAULT_HOST = "localhost"
private val DEFAULT_PORT = 8080
private val DEFAULT_DATABASE = "/services.json"

val PORT: Int
    get() {
        val systemPort = System.getenv("PORT")
        when (systemPort) {
            null -> return DEFAULT_PORT
            else -> return Integer.valueOf(systemPort)
        }
    }

val HOST: String
    get() {
        return System.getenv("HOST") ?: DEFAULT_HOST
    }

val DATABASE: String
    get() {
        return System.getenv("DATABASE") ?: DEFAULT_DATABASE
    }