package com.nogeeksbrewing.api.data

import org.springframework.data.annotation.Id

data class Batch(@Id val id: Long,
                 val foreignId: String,
                 val name: String,
                 val batchNumber: Long,
                 val brewDate: Long,
                 val status: String,
                 val style: String) {

    constructor(batch: BrewfatherBatch) : this(0,
        batch.id,
        batch.name,
        batch.batchNumber,
        batch.brewDate,
        batch.status,
        batch.recipe.style.name)

    constructor(batch: Batch) : this (batch.id,
        batch.foreignId,
        batch.name,
        batch.batchNumber,
        batch.brewDate,
        batch.status,
        batch.style)

}