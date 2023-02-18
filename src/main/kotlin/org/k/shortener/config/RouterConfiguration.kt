package org.k.shortener.config

import kotlinx.coroutines.reactor.awaitSingle
import org.k.shortener.handler.makeShortUrlHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfiguration {
    @Bean("mainRouter")
    fun mainRouter() = coRouter {
        GET("/{shortUrl}", makeShortUrlHandler("shortUrl"))
        accept(MediaType.APPLICATION_JSON).nest {
            POST("/url") { request ->
                request.awaitBody<CreateShortUrlDto>()
                    .let {
                        ServerResponse
                            .ok()
                            .bodyValue(it)
                            .awaitSingle()
                    }
            }
        }
    }
}
