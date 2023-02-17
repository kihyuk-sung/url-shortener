package org.k.shortener.config

import org.k.shortener.handler.makeShortUrlHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfiguration {
    @Bean("mainRouter")
    fun mainRouter() = coRouter {
        GET("/{shortUrl}", makeShortUrlHandler("shortUrl"))
    }
}
