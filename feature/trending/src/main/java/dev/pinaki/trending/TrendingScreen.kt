package dev.pinaki.trending

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.pinaki.medialist.MediaListScreen

@Composable
fun TrendingScreenDestination(
    modifier: Modifier = Modifier,
    viewModel: TrendingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    MediaListScreen(
        modifier = modifier,
        title = stringResource(R.string.trending),
        isLoading = uiState.isLoading,
        media = uiState.trendingList,
        errorMessage = uiState.errorMessage?.let { stringResource(id = it) },
        retryMessage = stringResource(R.string.retry),
        onRetry = viewModel::load
    )
}