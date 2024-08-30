package dev.sanastasov.arrowfx.dogceo

import arrow.core.merge
import arrow.fx.coroutines.parZip
import arrow.fx.coroutines.raceN
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import okhttp3.HttpUrl

suspend fun DogCeoApi.imageAndFact(
    breedName: String
): Pair<HttpUrl, String> = coroutineScope {
    val imageUrlD = async { imageUrl(breedName) }
    val funFactD = async { funFact(breedName) }
    imageUrlD.await().second to funFactD.await()
}

suspend fun DogCeoApi.imageAndFactFx(
    breedName: String
): Pair<HttpUrl, String> = parZip(
    { imageUrl(breedName) },
    { funFact(breedName) }
) { pair, fact -> pair.second to fact }

suspend fun cachedBreeds(): List<String> = TODO()

suspend fun DogCeoApi.fastBreedNames(): List<String> =
    raceN(
        { cachedBreeds() },
        { breedNames() }
    ).merge()
