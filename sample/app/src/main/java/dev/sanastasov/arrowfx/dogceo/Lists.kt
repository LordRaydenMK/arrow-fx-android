package dev.sanastasov.arrowfx.dogceo

import arrow.fx.coroutines.parMap
import arrow.fx.coroutines.parMapNotNull
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import okhttp3.HttpUrl
import java.io.IOException

/**
 * Fetch breed names
 * Sequentially fetch imageUrls
 */
suspend fun DogCeoApi.naiveApproach(
): List<Pair<String, HttpUrl>> =
    breedNames().map { imageUrl(it) }

suspend fun DogCeoApi.parallelOne(
): List<Pair<String, HttpUrl>> =
    breedNames().map {
        coroutineScope {
            async { imageUrl(it) }
        }
    }.awaitAll()

suspend fun DogCeoApi.parallelTwo(
): List<Pair<String, HttpUrl>> =
    coroutineScope {
        breedNames()
            .map { async { imageUrl(it) } }
            .awaitAll()
    }

suspend fun DogCeoApi.parallelThree(): List<Pair<String, HttpUrl>> =
    coroutineScope {
        breedNames()
            .map {
                async {
                    try {
                        imageUrl(it)
                    } catch (e: IOException) {
                        null
                    }
                }
            }
            .awaitAll()
            .filterNotNull()
    }

suspend fun DogCeoApi.parallelFour(
): List<Pair<String, HttpUrl>> {
    val semaphore = Semaphore(10)
    return coroutineScope {
        breedNames()
            .map { async { semaphore.withPermit { imageUrl(it) } } }
            .awaitAll()
    }
}

suspend fun DogCeoApi.arrowFx(
): List<Pair<String, HttpUrl>> =
    breedNames().parMap { imageUrl(it) }

suspend fun DogCeoApi.arrowFxTwo(
): List<Pair<String, HttpUrl>> =
    breedNames().parMapNotNull {
        try {
            imageUrl(it)
        } catch (e: IOException) {
            null
        }
    }

suspend fun DogCeoApi.arrowFxThree(
): List<Pair<String, HttpUrl>> =
    breedNames().parMap(concurrency = 12) {
        println(Thread.currentThread().name)
        imageUrl(it)
    }