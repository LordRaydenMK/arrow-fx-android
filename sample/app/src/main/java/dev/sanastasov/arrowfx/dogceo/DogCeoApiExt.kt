package dev.sanastasov.arrowfx.dogceo

import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl


suspend fun DogCeoApi.breedNames(): List<String> =
    allBreedNames()["message"]!!.jsonObject.keys.toList()

suspend fun DogCeoApi.imageUrl(name: String): Pair<String, HttpUrl> =
    name to imagesByBreed(name)["message"]!!.jsonArray.first().jsonPrimitive.content.toHttpUrl()


suspend fun DogCeoApi.naiveApproach(): List<Pair<String, HttpUrl>> {
    val breedNames = breedNames()
    return breedNames.map { imageUrl(it) }
}
