package org.k.shortener.persistant

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Repository

@Repository
class ShortUrlRepository(
    private val template: R2dbcEntityTemplate,
) {
    suspend fun save(shortUrlEntity: ShortUrlEntity): ShortUrlEntity = template
        .insert(ShortUrlEntity::class.java)
        .into("short_urls")
        .using(shortUrlEntity)
        .awaitSingle()

    fun findAll(): Flow<ShortUrlEntity> = template
        .select(ShortUrlEntity::class.java)
        .from("short_urls")
        .all()
        .asFlow()

}
