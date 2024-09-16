package org.demo

import io.quarkus.runtime.Startup
import io.quarkus.websockets.next.*
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.StaticHandler
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import java.io.InputStreamReader

@WebSocket(path = "/")
@ApplicationScoped
class Gateway {



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
    fun onOpen(session: WebSocketConnection) {
        println("Connection opened: ${session.id()}")
    }

    @OnTextMessage
    fun onMessage(message: String, session: WebSocketConnection) {
        println("Connection on: ${session.id()}")
        println("Received: $message")
        session.sendTextAndAwait(message)
    }

    @OnClose
    fun onClose(session: WebSocketConnection) {
        println("Connection closed: ${session.id()}")
    }


}
