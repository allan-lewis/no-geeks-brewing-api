package com.nogeeksbrewing.api.batch

import com.nogeeksbrewing.api.data.Batch
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux

@Controller
class BatchController(val batchService: BatchService) {

    @QueryMapping()
    fun batches(@Argument status: String): Flux<Batch> {
        return batchService.batches(status)
    }

}
