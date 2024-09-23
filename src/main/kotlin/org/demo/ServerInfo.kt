package org.demo

import jakarta.ws.rs.GET
import jakarta.ws.rs.HeaderParam
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.jboss.resteasy.reactive.RestResponse
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder


@Path(value = "/")
class ServerInfo {

    private fun jsonInfo(): String {
        return """
            {
              "icon": "https://image.nostr.build/fc4a04e980020ed876874fa0142edd9fc22774efa8fa067f96285f2f44965e38.jpg",
              "description": "Hello, World!"
            }
        """.trimIndent()
    }

    @GET
    fun handleRequest(@HeaderParam("accept") acceptHeader: String?): RestResponse<String> {
        println("Accept Header: $acceptHeader")
        return ResponseBuilder.ok(jsonInfo(), MediaType.APPLICATION_JSON).build()
    }

}