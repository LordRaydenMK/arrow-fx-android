package dev.sanastasov.arrowfx.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.sanastasov.arrowfx.dogceo.DogCeoApi
import dev.sanastasov.arrowfx.dogceo.naiveApproach
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class DogListViewModel(private val api: DogCeoApi) : ViewModel() {

    val viewState: StateFlow<DogListViewState> = flow {
        val dogs = api.naiveApproach()
            .map { (name, url) -> DogBreed(name, url) }
        emit(DogListViewState.Content(dogs))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), DogListViewState.Loading)
}