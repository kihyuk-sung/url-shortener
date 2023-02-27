package org.k.shortener.domain

data class ShortUrlDto<T>(
    val id: T,
    val shortUrl: String,
    val url: String,
)
