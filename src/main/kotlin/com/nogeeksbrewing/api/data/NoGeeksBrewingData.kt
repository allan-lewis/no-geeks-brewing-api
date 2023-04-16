package com.nogeeksbrewing.api.data

import org.springframework.data.annotation.Id

data class Batch(@Id val id: Long,
                 val foreignId: String,
                 val name: String,
                 val batchNumber: Long,
                 val brewDate: Long,
                 val status: String,
                 val style: String) {

    constructor(id: Long, brewfatherBatch: BrewfatherBatch) : this(id,
        brewfatherBatch.id,
        brewfatherBatch.name,
        brewfatherBatch.batchNumber,
        brewfatherBatch.brewDate,
        brewfatherBatch.status,
        brewfatherBatch.recipe.style.name)

}