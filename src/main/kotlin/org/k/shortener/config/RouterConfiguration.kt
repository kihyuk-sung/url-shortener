package org.k.shortener.config

import org.k.shortener.handler.handleCreateShortUrl
import org.k.shortener.handler.makeShortUrlHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfiguration {
    @Bean("mainRouter")
    fun mainRouter() = coRouter {
        GET("/{shortUrl}", makeShortUrlHandler("shortUrl"))
        accept(MediaType.APPLICATION_JSON).nest {
            POST("/url", ::handleCreateShortUrl)
        }
    }
}
