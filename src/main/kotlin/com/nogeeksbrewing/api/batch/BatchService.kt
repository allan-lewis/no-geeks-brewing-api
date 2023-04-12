package com.nogeeksbrewing.api.batch

import com.nogeeksbrewing.api.data.Batch
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux

@Component
class BatchService(val repository: BatchRepository) {

    fun batches(): Flux<Batch> {
        return repository.findAll()
    }

}