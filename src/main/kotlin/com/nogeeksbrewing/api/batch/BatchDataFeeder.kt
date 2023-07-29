package com.nogeeksbrewing.api.batch

import com.nogeeksbrewing.api.data.Batch
import com.nogeeksbrewing.api.data.BrewfatherBatch
import io.github.oshai.KotlinLogging
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.lang.Exception
import java.net.URI
import java.util.*
import kotlin.concurrent.timerTask

private val logger = KotlinLogging.logger {}

@Component
class BatchDataFeeder(private val batchRepository: BatchRepository,
                      @Value("\${ngb.brewfather.batchSize}") private val batchSize: Int,
                      @Value("\${ngb.brewfather.url}") private val url: String,
                      @Value("\${ngb.brewfather.auth.token}") private val authToken: String) : ApplicationRunner {

    private val restTemplate = RestTemplate()

    @PostConstruct
    private fun init() {
        logger.info { "batchSize: $batchSize" }
        logger.info { "url: $url" }
        logger.info { "authToken: **** (length ${authToken.length})" }
    }

    override fun run(args: ApplicationArguments?) {
        logger.info { "running" }

        Timer().scheduleAtFixedRate(timerTask { try { load() } catch (e: Exception) { logger.error("error in load", e) } },
            5000,
            60000)
    }

    private fun load() {
        val headers = HttpHeaders()
        headers.set("Authorization", "Basic $authToken")

        var last = ""
        var keepGoing = true

        while (keepGoing) {
            val requestEntity = RequestEntity<String>(headers, HttpMethod.GET, URI.create(url + last))

            val responseEntity = restTemplate.exchange(requestEntity, Array<BrewfatherBatch>::class.java)

            logger.info { "response batch(es): ${responseEntity.body!!.size}" }

            for (batch in responseEntity.body!!) {
                batchRepository.findByForeignId(batch.id)
                    .defaultIfEmpty(Batch(0, batch))
                    .subscribe{ before ->
                        logger.info { "brewfather $batch" }
                        logger.info { "ngb (before) $before" }
                        batchRepository.save(Batch(before.id, batch)).subscribe{ after ->  logger.info { "ngb (after) $after" }}
                    }

                last = batch.id
            }

            if (responseEntity.body!!.isEmpty()) {
                keepGoing = false
            }

            batchRepository.count().subscribe { x -> logger.info { "repository batch(es): $x" } }
        }
    }

}
