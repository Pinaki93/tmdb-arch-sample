package dev.pinaki.trending

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.pinaki.model.Media

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendingScreen(
    modifier: Modifier = Modifier,
    viewModel: TrendingViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = modifier,
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
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        TrendingScreenContent(
            modifier = Modifier.padding(innerPadding),
            isLoading = uiState.isLoading,
            errorMessage = uiState.errorMessage,
            trendingList = uiState.trendingList
        )
    }
}

@Composable
fun TrendingScreenContent(
    isLoading: Boolean,
    errorMessage: String?,
    trendingList: List<Media>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        if (isLoading) {
            Text(
                text = "Loading"
            )
        } else if (errorMessage != null) {
            Text(
                text = errorMessage
            )
        } else {
            Text(
                text = "Trending List: ${trendingList.map { it.title }.joinToString(separator = "\n")}"
            )
        }
    }
}