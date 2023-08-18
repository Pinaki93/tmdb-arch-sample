package dev.pinaki.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TwoLineListItem(
    modifier: Modifier = Modifier,
    image: @Composable (modifier: Modifier) -> Unit,
    title: @Composable (modifier: Modifier) -> Unit,
    subtitle: @Composable (modifier: Modifier) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        image(
            modifier = Modifier.size(40.dp)
        )
        Spacer(
            modifier = Modifier.width(8.dp)
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            title(modifier = Modifier)
            subtitle(Modifier)
        }
    }
}