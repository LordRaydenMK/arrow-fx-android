package dev.sanastasov.arrowfx.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import okhttp3.HttpUrl.Companion.toHttpUrl

@Composable
fun DogsListScreen(state: DogListViewState, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        when (state) {
            is DogListViewState.Content -> Content(state.data)
            DogListViewState.Loading -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun Content(data: List<DogBreed>) {
    LazyVerticalGrid(GridCells.Adaptive(175.dp)) {
        items(data) {
            Box(modifier = Modifier.aspectRatio(1f)) {
                AsyncImage(
                    model = it.imageUrl,
                    contentDescription = it.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text = it.name,
                    Modifier.align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .background(Color.White.copy(alpha = 0.5f))
                        .padding(8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun LoadingPreview() {
    DogsListScreen(state = DogListViewState.Loading)
}

@Preview
@Composable
fun ContentPreview() {
    DogsListScreen(
        state = DogListViewState.Content(
            listOf(
                DogBreed(
                    "Hound",
                    "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg".toHttpUrl()
                ),
                DogBreed(
                    "Hound",
                    "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg".toHttpUrl()
                ),
                DogBreed(
                    "Hound",
                    "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg".toHttpUrl()
                )
            )
        )
    )
}