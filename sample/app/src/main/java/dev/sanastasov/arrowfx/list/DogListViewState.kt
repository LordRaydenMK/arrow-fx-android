package dev.sanastasov.arrowfx.list

import okhttp3.HttpUrl

data class DogBreed(
    val name: String,
    val imageUrl: HttpUrl,
)

sealed class DogListViewState {
    data object Loading : DogListViewState()
    data class Content(val data: List<DogBreed>):  DogListViewState()
}