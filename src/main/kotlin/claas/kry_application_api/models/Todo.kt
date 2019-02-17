package claas.kry_application_api.models

import claas.kry_application_api.common.HOST
import claas.kry_application_api.common.PORT
import java.util.*

data class Todo(
        var id: UUID? = UUID.randomUUID(),
        val title: String?,
        val completed: Boolean = false
) {
    var url : String = ""
        get() { return "http://$HOST:$PORT/api/todos/${id}" }
}