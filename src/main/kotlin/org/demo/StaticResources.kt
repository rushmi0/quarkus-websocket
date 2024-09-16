package org.demo

import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.StaticHandler
import jakarta.inject.Inject

class StaticResources {

    @Inject
    lateinit var router: Router

    fun index() {
        router.route("public/*").handler(StaticHandler.create("public/"))
    }

}