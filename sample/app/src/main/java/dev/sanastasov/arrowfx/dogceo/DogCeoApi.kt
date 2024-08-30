package dev.sanastasov.arrowfx.dogceo

import kotlinx.serialization.json.JsonObject
import retrofit2.http.GET
import retrofit2.http.Path

interface DogCeoApi {

    @GET("breeds/list/all")
    suspend fun allBreedNames(): JsonObject

    @GET("breed/{breed}/images/random")
    suspend fun imagesByBreed(@Path("breed")  name: String): JsonObject
}