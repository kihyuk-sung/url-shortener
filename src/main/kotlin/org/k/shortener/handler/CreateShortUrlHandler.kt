package org.k.shortener.handler

import kotlinx.coroutines.reactor.awaitSingle
import org.k.shortener.config.CreateShortUrlDto
import org.k.shortener.domain.createShortUrl
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody

suspend fun handleCreateShortUrl(serverRequest: ServerRequest): ServerResponse = serverRequest
    .awaitBody<CreateShortUrlDto>()
    .let { createShortUrl(it.url) }
    .let(::ShortUrlDto)
    .let {
        ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(it)
            .awaitSingle()
    }

