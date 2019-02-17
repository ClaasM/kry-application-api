# KRY Application - API

Based on [this](https://github.com/kanzitelli/kotlin-vertx-boilerplate) boilerplate.

### Prerequisites
* Make sure you have [JAVA installed](https://www.java.com/en/download/help/download_options.xml)
* Install [SDKMAN](http://sdkman.io/install.html)
* Install [Kotlin lang](https://kotlinlang.org/docs/tutorials/command-line.html#downloading-the-compiler) using **SDKMAN**
* Install [Gradle](https://gradle.org/install/) using **SDKMAN**

### How to run:

* Clone the repo: `git clone https://github.com/ClaasM/kry-application-api.git`
* `$ cd kry-application-api/`
* Create the empty "database" `echo [] > /your/path/services.json`
* Set the `DATABASE` env-variable to the files absolute path
* Set the `HOST`and `PORT` env-variables if you do not want to use the default `localhost:8080`
* `$ gradle build`
* `$ gradle run`
* or to build a Jar and run in production mode
```sh
$ gradle shadowJar
$ java -jar build/libs/app-shadow.jar
``` 


### API ENDPOINTS

`HTTP GET /services` returns all services and their status.
`HTTP POST /services` adds a new service to check with name and URL and
assigns a random id.
`HTTP DELETE /services/{service_id}` removes the service with the
specified service_id.

Additionally:

`HTTP PUT /service/{service_id}` to update a service's status.