package org.k.shortener.domain

import org.k.shortener.domain.id.toBase62
import java.util.UUID

fun createShortUrl(url: String): String = UUID
    .randomUUID()
    .let {
        ShortUrlDto(
            id = it,
            shortUrl = it.toBase62(),
            url = url,
        )
    }
    .shortUrl
