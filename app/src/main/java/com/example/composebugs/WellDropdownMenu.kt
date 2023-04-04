package com.example.composebugs

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.composebugs.WellTextInputLayout

@Composable
fun WellDropdownMenu(
    modifier: Modifier = Modifier,
    defaultValue: String? = null,
    hint: String,
    items: List<String>,
    onSelect: (Int, String) -> Unit = { _, _ -> },
    enabled: Boolean = true,
    errorMessage: String? = null
) {
    WellTextInputLayout(
        modifier = modifier,
        value = defaultValue,
        hint = hint,
        enabled = enabled,
        isDropdownMenu = true,
        errorMessage = errorMessage,
        update = {

        }
    )
}
