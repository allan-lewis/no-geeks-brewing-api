package com.nogeeksbrewing.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@Configuration
@EnableR2dbcRepositories
class NoGeeksBrewingConfiguration

@SpringBootApplication
class NoGeeksBrewingApplication

fun main(args: Array<String>) {
    runApplication<NoGeeksBrewingApplication>(*args)
}
