package org.k.shortener.persistant

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.k.shortener.domain.ShortUrlDto
import org.k.shortener.domain.id.toByteArray
import org.k.shortener.domain.id.toUUID
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class ShortUrlAdapter(
    private val shortUrlRepository: ShortUrlRepository,
) {
    suspend fun save(shortUrl: ShortUrlDto<UUID>): ShortUrlDto<UUID> {
        val entity = ShortUrlEntity(
            id = shortUrl.id.toByteArray(),
            shortUrl = shortUrl.shortUrl,
            url = shortUrl.url,
        )
        return shortUrlRepository.save(entity)
            .let {
                ShortUrlDto(
                    id = it.id.toUUID(),
                    shortUrl = it.shortUrl,
                    url = it.url,
                )
            }
    }

    fun findAll(): Flow<ShortUrlDto<UUID>> = shortUrlRepository
        .findAll()
        .map { ShortUrlDto(
            id = it.id.toUUID(),
            shortUrl = it.shortUrl,
            url = it.url,
        ) }
}
