package com.example.composebugs

import android.text.InputType
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

// https://issuetracker.google.com/issues/259972885
// https://issuetracker.google.com/issues/257209915
// https://issuetracker.google.com/issues/244478720
// https://issuetracker.google.com/issues/258154759
@Composable
fun WellTextInputLayout(
    modifier: Modifier,
    value: String?,
    hint: String,
    enabled: Boolean = true,
    isDropdownMenu: Boolean = false,
    errorMessage: String? = null,
    update: (TextInputLayout) -> Unit = { _ -> }
) {
    val style = com.google.android.material.R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox

    AndroidView(
        modifier = modifier,
        factory = { context ->
            TextInputLayout(
                ContextThemeWrapper(
                    context,
                    style
                )
            ).apply {
                layoutParams =
                    ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                if (isDropdownMenu) {
                    addView(
                        MaterialAutoCompleteTextView(
                            ContextThemeWrapper(
                                context,
                                style
                            )
                        )
                    )
                } else {
                    this.endIconMode = TextInputLayout.END_ICON_CUSTOM
                    val textInputEditText = TextInputEditText(
                        ContextThemeWrapper(
                            context,
                            style
                        )
                    ).apply {
                        this.isLongClickable = false
                        this.inputType = InputType.TYPE_NULL
                        this.isFocusable = false
                    }
                    addView(textInputEditText)
                }
            }
        },
        update = { textInputLayout ->
            textInputLayout.apply {
                this.hint = hint
                this.isEnabled = enabled
                this.editText?.setText(value)
                this.error = errorMessage
                this.isErrorEnabled = errorMessage != null
            }
            update.invoke(textInputLayout)
        }
    )
}
