package dev.sanastasov.arrowfx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.sanastasov.arrowfx.list.DogListViewModel
import dev.sanastasov.arrowfx.list.DogsListScreen
import dev.sanastasov.arrowfx.ui.theme.ArrowFxTheme

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<DogListViewModel> {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val api = (application as App).dogCeoApi
                return DogListViewModel(api) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArrowFxTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val state by viewModel.viewState.collectAsState()

                    DogsListScreen(state = state, Modifier.padding(innerPadding))
                }
            }
        }
    }
}