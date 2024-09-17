package org.demo

import io.quarkus.runtime.Startup
import io.quarkus.vertx.web.Header
import io.quarkus.vertx.web.Route
import io.quarkus.websockets.next.*
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import java.io.InputStreamReader

@WebSocket(path = "/")
@ApplicationScoped
class Gateway {

    @Inject
    lateinit var session: WebSocketConnection

    @Startup
    fun init() {
        val classLoader = Thread.currentThread().contextClassLoader
        val bannerInputStream = classLoader.getResourceAsStream("banner.txt")

        bannerInputStream?.let {
            val bannerText = InputStreamReader(it).readText()
            println(bannerText)
        } ?: println("Banner not found.")
    }

    @OnOpen
    fun onOpen() {

        session.isOpen.run {
            println("Connection opened: ${session.id()}")
        }

        //println(accept[1])

        //loadServerInfo()

    }

    @OnTextMessage
    fun onMessage(message: String) {
        println("Connection on: ${session.id()}")
        println("Received: $message")
        session.sendTextAndAwait("O.K.!!!")
    }

    @OnClose
    fun onClose() {
        println("Connection closed: ${session.id()}")
    }

}
