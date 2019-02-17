package claas.kry_application_api.routes

import claas.kry_application_api.common.coroutineHandler
import claas.kry_application_api.handlers.*
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import claas.kry_application_api.common.API_ENDPOINT
import claas.kry_application_api.handlers.ConfigHandlers
import claas.kry_application_api.handlers.SimpleHandlers
import claas.kry_application_api.handlers.TodosHandlers

class Routes(val vertx: Vertx) {
    fun createRouter(): Router {
        val ConfigHandlers = ConfigHandlers()
        val SimpleHandlers = SimpleHandlers()
        val TodosHandlers = TodosHandlers()

        return Router.router(vertx).apply {
            // CONFIG ROUTES
            route().handler(ConfigHandlers.corsHandler)
            route().handler(ConfigHandlers.bodyHandler)

            // SIMPLE ROUTES
            get("/home")      .coroutineHandler { SimpleHandlers.homeJsonHandler(it) }
            get("/home.json") .coroutineHandler { SimpleHandlers.homeJsonHandler(it) }
            get("/hello")     .coroutineHandler { SimpleHandlers.helloJsonHandler(it) }
            get("/hello.json").coroutineHandler { SimpleHandlers.helloJsonHandler(it) }

            // TODOS ROUTES
            get   ("$API_ENDPOINT/todos")    .coroutineHandler { TodosHandlers.getTodos(it) }
            get   ("$API_ENDPOINT/todos/:id").coroutineHandler { TodosHandlers.getTodoById(it) }
            post  ("$API_ENDPOINT/todos")    .coroutineHandler { TodosHandlers.createNewTodo(it) }
            put   ("$API_ENDPOINT/todos/:id").coroutineHandler { TodosHandlers.updateTodoById(it) }
            delete("$API_ENDPOINT/todos/:id").coroutineHandler { TodosHandlers.removeTodoById(it) }

            // route("/public/*").handler(ConfigHandlers.staticHandler)
            route().handler { ConfigHandlers.otherPageHandler(it) }
        }
    }
}