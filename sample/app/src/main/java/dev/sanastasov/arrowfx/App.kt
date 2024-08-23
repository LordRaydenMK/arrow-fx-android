package dev.sanastasov.arrowfx

import android.app.Application
import dev.sanastasov.arrowfx.dogceo.DogCeoApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

class App : Application() {

    lateinit var dogCeoApi: DogCeoApi

    override fun onCreate() {
        super.onCreate()
        val json = Json { ignoreUnknownKeys = true }
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        dogCeoApi = retrofit.create()
    }
}