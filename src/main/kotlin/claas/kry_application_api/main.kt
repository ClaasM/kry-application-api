package claas.kry_application_api

import claas.kry_application_api.models.ServiceStatus
import claas.kry_application_api.models.ServiceStatusAdapter
import com.beust.klaxon.Converter
import com.beust.klaxon.JsonArray
import com.beust.klaxon.Klaxon
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.net.URL
import java.util.*

fun main(args : Array<String>) {


    val gsonBuilder = GsonBuilder()
    GsonBuilder().registerTypeAdapter(ServiceStatus::class.java, ServiceStatusAdapter())
    val gson = gsonBuilder.create()


    val article = gson.fromJson("""[{
        "serviceURL": "google.com"
    }]""", Array<ServiceStatus>::class.java)

    println(gson.toJson(article))





    /*
    val vertx = Vertx.vertx()
    vertx.deployVerticle(MainVerticle()) { ar ->
        if (ar.succeeded()) {
            println("Application started")
        } else {
            println("Could not start application")
            ar.cause().printStackTrace()
        }
    }
    */
}