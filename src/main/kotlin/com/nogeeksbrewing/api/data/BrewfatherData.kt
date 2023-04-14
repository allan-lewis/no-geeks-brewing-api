package com.nogeeksbrewing.api.data

import com.fasterxml.jackson.annotation.JsonProperty

data class BrewfatherBatch(@JsonProperty("_id") val id: String,
                           @JsonProperty val name: String,
                           @JsonProperty val brewDate: Long,
                           @JsonProperty val status: String,
                           @JsonProperty("batchNo") val batchNumber: Long,
                           @JsonProperty val recipe: BrewfatherRecipe)

data class BrewfatherRecipe(@JsonProperty val name: String,
                            @JsonProperty val style: BrewfatherStyle)

data class BrewfatherStyle(@JsonProperty val name: String)