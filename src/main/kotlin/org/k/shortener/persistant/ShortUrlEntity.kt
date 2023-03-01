package org.k.shortener.persistant

data class ShortUrlEntity(
    val id: ByteArray,
    val shortUrl: String,
    val url: String,
)
