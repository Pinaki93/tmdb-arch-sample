package dev.pinaki.medialist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.pinaki.composables.TwoLineListItem
import dev.pinaki.util.DevicePreviews
import dev.pinaki.util.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaListScreen(
    modifier: Modifier = Modifier,
    title: String,
    isLoading: Boolean,
    media: List<MediaUiState>,
    errorMessage: String?,
    retryMessage: String,
    onRetry: () -> Unit,
) {
    Scaffold(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title
                    )
                }
            )
        }
    ) { innerPadding ->
        MediaListContent(
            modifier = Modifier.padding(innerPadding),
            isLoading = isLoading,
            media = media,
            errorMessage = errorMessage,
            retryMessage = retryMessage,
            onRetry = onRetry
        )
    }
}

@Composable
fun MediaListContent(
    isLoading: Boolean,
    media: List<MediaUiState>,
    errorMessage: String?,
    retryMessage: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                        .align(Alignment.Center),
                )
            }

            errorMessage != null -> {
                ErrorContent(
                    modifier = Modifier.align(Alignment.Center),
                    errorMessage = errorMessage,
                    retryMessage = retryMessage,
                    onRetry = onRetry
                )
            }

            else -> {
                MediaList(media)
            }
        }
    }
}

@Composable
fun ErrorContent(
    errorMessage: String,
    retryMessage: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = errorMessage,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Button(onClick = onRetry) {
            Text(text = retryMessage)
        }
    }
}

@Composable
private fun MediaList(media: List<MediaUiState>) {
    LazyColumn {
        items(
            items = media,
            key = { it.id }
        ) { media ->
            TwoLineListItem(
                image = {
                    AsyncImage(
                        modifier = Modifier.size(40.dp),
                        model = media.posterUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                },
                title = {
                    Text(
                        text = media.title,
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                subtitle = {
                    Text(
                        text = stringResource(resource = media.ratingText),
                        style = MaterialTheme.typography.labelSmall.copy(color = Color.Gray),
                        modifier = Modifier
                            .alpha(0.7f)
                    )
                }
            )
        }
    }
}

@DevicePreviews
@Composable
fun MediaListLoadingPreview() {
    MediaListScreen(
        title = "Test Title",
        isLoading = true,
        errorMessage = null,
        media = emptyList(),
        retryMessage = "",
        onRetry = {},
    )
}

@Preview(name = "phone", device = Devices.PIXEL_C, widthDp = 400, heightDp = 800)
@Composable
fun MediaListErrorPreview() {
    MediaListScreen(
        title = "Test Title",
        isLoading = false,
        errorMessage = "An Error Occurred",
        media = emptyList(),
        retryMessage = "Retry",
        onRetry = {}
    )
}