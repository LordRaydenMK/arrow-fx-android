package dev.sanastasov.arrowfx.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.sanastasov.arrowfx.dogceo.DogCeoApi
import dev.sanastasov.arrowfx.list.DogListViewState.Loading
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlin.time.measureTimedValue

private const val TAG = "DogListViewModel"

class DogListViewModel(
    private val api: DogCeoApi,
    private val loadDogs: LoadDogs,
) : ViewModel() {

    val viewState: StateFlow<DogListViewState> = flow {
        val (dogs, duration) = measureTimedValue {
            api.loadDogs()
                .map { (name, url) -> DogBreed(name, url) }
        }
        Log.d(TAG, "Duration: $duration")
        emit(DogListViewState.Content(dogs))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), Loading)
}