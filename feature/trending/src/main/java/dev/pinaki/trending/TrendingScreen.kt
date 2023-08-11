package dev.pinaki.trending

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.pinaki.util.DevicePreviews

@Composable
fun TrendingScreenDestination(
    modifier: Modifier = Modifier,
    viewModel: TrendingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TrendingScreen(
        modifier = modifier,
        isLoading = uiState.isLoading,
        errorMessage = uiState.errorMessage?.let { stringResource(id = it) },
        trendingList = uiState.trendingList,
        retryMessage = stringResource(R.string.retry),
        onRetry = viewModel::load
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendingScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    trendingList: List<MediaUiState>,
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
                        text = stringResource(R.string.trending)
                    )
                }
            )
        }
    ) { innerPadding ->
        TrendingScreenContent(
            modifier = Modifier.padding(innerPadding),
            isLoading = isLoading,
            trendingList = trendingList,
            errorMessage = errorMessage,
            retryMessage = retryMessage,
            onRetry = onRetry
        )
    }
}

@Composable
fun TrendingScreenContent(
    isLoading: Boolean,
    trendingList: List<MediaUiState>,
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
                MediaList(trendingList)
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
private fun MediaList(trendingList: List<MediaUiState>) {
    LazyColumn {
        items(
            items = trendingList,
            key = { it.id }
        ) { media ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = media.title,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
            }
        }
    }
}

@DevicePreviews
@Composable
fun TrendingScreenLoadingPreview() {
    TrendingScreen(
        isLoading = true,
        errorMessage = null,
        trendingList = emptyList(),
        retryMessage = "",
        onRetry = {}
    )
}

@Preview(name = "phone", device = Devices.PIXEL_C, widthDp = 400, heightDp = 800)
@Composable
fun TrendingScreenErrorPreview() {
    TrendingScreen(
        isLoading = false,
        errorMessage = "An Error Occurred",
        trendingList = emptyList(),
        retryMessage = "Retry",
        onRetry = {}
    )
}

@Preview(name = "phone", device = Devices.PIXEL_C, widthDp = 400, heightDp = 800)
@Composable
fun TrendingScreenContent() {
    TrendingScreen(
        isLoading = false,
        errorMessage = null,
        trendingList = listOf(
            MediaUiState(
                title = "Midsommer",
                id = 0,
            ),
            MediaUiState(
                id = 1,
                title = "Eternal Sunshine Of The Spotless Mind"
            ),
            MediaUiState(
                id = 2,
                title = "Hereditary"
            ),
            MediaUiState(
                id = 3,
                title = "Beau Is Afraid"
            ),
        ),
        retryMessage = "Retry",
        onRetry = {}
    )
}