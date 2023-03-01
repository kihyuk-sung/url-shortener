package org.k.shortener.config

import kotlinx.coroutines.reactor.awaitSingle
import org.k.shortener.domain.ShortUrlDto
import org.k.shortener.domain.id.toBase62
import org.k.shortener.handler.makeShortUrlHandler
import org.k.shortener.persistant.ShortUrlAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.coRouter
import java.util.*

@Configuration
class RouterConfiguration(
    private val shortUrlAdapter: ShortUrlAdapter,
) {
    @Bean("mainRouter")
    fun mainRouter() = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            POST("/url") {
                val id = UUID.randomUUID()
                val body = shortUrlAdapter.save(
                    ShortUrlDto(
                        id = id,
                        shortUrl = id.toBase62(),
                        url = id.toBase62(),
                    )
                )
                ServerResponse
                    .ok()
                    .bodyValue(body)
                    .awaitSingle()
            }

            GET("/url") {
                ServerResponse
                    .ok()
                    .bodyAndAwait(shortUrlAdapter.findAll())
            }
        }
        GET("/{shortUrl}", makeShortUrlHandler("shortUrl"))
    }
}
