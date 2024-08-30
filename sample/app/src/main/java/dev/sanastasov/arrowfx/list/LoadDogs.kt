package dev.sanastasov.arrowfx.list

import dev.sanastasov.arrowfx.dogceo.DogCeoApi
import okhttp3.HttpUrl

typealias LoadDogs = suspend DogCeoApi.() -> List<Pair<String, HttpUrl>>