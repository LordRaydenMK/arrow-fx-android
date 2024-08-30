package dev.sanastasov.arrowfx.dogceo

import arrow.fx.coroutines.parMapNotNull
import arrow.resilience.Schedule
import arrow.resilience.retry
import okhttp3.HttpUrl
import java.io.IOException
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

val schedule: Schedule<Throwable, Any> = Schedule.exponential<Throwable>(10.milliseconds)
    .doWhile { _, duration -> duration < 60.seconds }
    .andThen(Schedule.spaced<Throwable>(60.seconds) and Schedule.recurs(10))
    .jittered()

suspend fun DogCeoApi.arrowFxFour(): List<Pair<String, HttpUrl>> =
    breedNames().parMapNotNull {
        try {
            schedule.retry { imageUrl(it) }
        } catch (e: IOException) {
            null
        }
    }
