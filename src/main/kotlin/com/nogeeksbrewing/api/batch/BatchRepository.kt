package com.nogeeksbrewing.api.batch

import com.nogeeksbrewing.api.data.Batch
import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Mono

interface BatchRepository : R2dbcRepository<Batch, String> {

    fun findByForeignId(foreignId: String): Mono<Batch>

}
