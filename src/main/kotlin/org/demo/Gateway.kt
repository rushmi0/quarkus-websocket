package org.demo

import io.quarkus.runtime.Startup
import io.quarkus.websockets.next.*

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

import org.jboss.resteasy.reactive.RestResponse
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder

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

    private fun jsonInfo(): String {
        return """
            {
              "icon": "https://image.nostr.build/fc4a04e980020ed876874fa0142edd9fc22774efa8fa067f96285f2f44965e38.jpg",
              "description": "Hello, World!"
            }
        """.trimIndent()
    }

    @OnOpen
    fun onOpen(): RestResponse<String> {

        session.isOpen.run {
            println("Connection opened: ${session.id()}")
            return ResponseBuilder.ok("Session opened!!").build()
        }

    }

    @OnTextMessage
    fun onMessage(message: String) {
        println("Connection on: ${session.id()}")
        session.sendTextAndAwait("O.K.!!!, $message")
    }

    @OnClose
    fun onClose() {
        println("Connection closed: ${session.id()}")
    }

}
