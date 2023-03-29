package com.example.composebugs.ui.theme // ktlint-disable filename

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun ComposeBugsTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        content = content
    )
}
