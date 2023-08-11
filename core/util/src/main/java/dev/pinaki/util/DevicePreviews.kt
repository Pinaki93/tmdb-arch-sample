package dev.pinaki.util

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "phone",
    device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480",
    widthDp = 360,
    heightDp = 640
)
@Preview(
    name = "phone_xl",
    widthDp = 420,
    heightDp = 800
)
annotation class DevicePreviews