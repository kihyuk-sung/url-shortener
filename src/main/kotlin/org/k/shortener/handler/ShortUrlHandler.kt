package org.k.shortener.handler

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

fun makeShortUrlHandler(pathName: String): suspend (ServerRequest) -> ServerResponse = {
    ServerResponse
        .ok()
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(it.pathVariable(pathName))
        .awaitSingle()
}
