@file:OptIn(ExperimentalMaterialApi::class)

package com.example.composebugs

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// https://issuetracker.google.com/issues/122476634

private const val OUTLINED_TEXT_FIELD_TOP_PADDING = 8
const val WELL_TEXT_FIELD_TEST_TAG = "WELL_TEXT_FIELD_TEST_TAG"
const val WELL_TEXT_FIELD_SHOW_PASSWORD_BUTTON_TEST_TAG = "WELL_TEXT_FIELD_SHOW_PASSWORD_BUTTON_TEST_TAG"
const val MOBILE_NUMBER_PLACEHOLDER = "(999) 123–4567"
private const val MOBILE_NUMBER_MASK = "(###) ###-####"
private const val DATE_HELPER_TEXT = "MM/DD/YYYY"
private const val DATE_MASK = "##/##/####"

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WellTextField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    inputType: InputType = InputType.TEXT,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: String,
    placeholder: @Composable (() -> Unit)? = null,
    errorMessage: String? = null,
    minLines: Int = 1,
    maxLines: Int = 1,
    isOutlinedTextField: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(),
    hideLabel: Boolean = false,
    minHeight: Dp = TextFieldDefaults.MinHeight
) {
    val softwareKeyboardController = LocalSoftwareKeyboardController.current

    val textColor = textStyle.color.takeOrElse {
        colors.textColor(enabled).value
    }

    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))
    // https://issuetracker.google.com/issues/251162419
    val contentDesc = if (hideLabel || placeholder != null) {
        label
    } else null

    val keyboardOptions: KeyboardOptions = when (inputType) {
        InputType.TEXT -> { KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text) }
        InputType.NUMBER -> { KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number) }
        InputType.NUMBER_DECIMAL -> { KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal) }
        InputType.PHONE -> { KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone) }
        InputType.EMAIL -> { KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email) }
        InputType.DATE -> { KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number) }
        InputType.PASSWORD -> { KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password) }
        InputType.CODE -> { KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number) }
        InputType.USERNAME -> { KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text) }
    }


    val mask: String? = when (inputType) {
        InputType.PHONE -> {
            MOBILE_NUMBER_MASK
        }
        InputType.DATE -> {
            DATE_MASK
        }
        else -> {
            null
        }
    }
    val maskLength = mask?.replace("[^#]".toRegex(), "")?.length ?: 0

    Column(
        modifier = modifier
            .width(IntrinsicSize.Min)
            .wrapContentHeight()
            .testTag(WELL_TEXT_FIELD_TEST_TAG),
        verticalArrangement = Arrangement.Center
    ) {
        val multiLineModifier = Modifier
            .fillMaxSize()

        BasicTextField(
            value = value,
            onValueChange = { value: TextFieldValue ->
//              https://issuetracker.google.com/issues/202353328
//              https://issuetracker.google.com/issues/225399256
                if (mask != null) {
                    if (value.text.length <= maskLength) onValueChange.invoke(value)
                } else if (keyboardOptions.keyboardType == KeyboardType.Number) {
                    if (!value.text.contains('.')) onValueChange.invoke(value)
                } else {
                    onValueChange.invoke(value)
                }
            },
            modifier = if (hideLabel && minHeight == TextFieldDefaults.MinHeight) {
                multiLineModifier
            } else {
                multiLineModifier.padding(top = OUTLINED_TEXT_FIELD_TOP_PADDING.dp)
            }
                .defaultMinSize(
                    minWidth = TextFieldDefaults.MinWidth,
                    minHeight = minHeight
                ),
            enabled = enabled,
            readOnly = readOnly,
            textStyle = mergedTextStyle,
            keyboardOptions = keyboardOptions,
            // https://issuetracker.google.com/issues/197121898
            keyboardActions = KeyboardActions(
                onDone = {
                    softwareKeyboardController?.hide()
                }
            ),
            interactionSource = interactionSource,
            singleLine = maxLines == 1,
            minLines = minLines,
            maxLines = maxLines,
            decorationBox = @Composable { innerTextField ->
                TextFieldDefaults.OutlinedTextFieldDecorationBox(
                    value = value.text,
                    innerTextField = innerTextField,
                    placeholder = placeholder,
                    label = label,
                    enabled = enabled,
                    interactionSource = interactionSource,
                    colors = colors,
                    border = {
                        TextFieldDefaults.BorderBox(
                            true,
                            false,
                            interactionSource,
                            TextFieldDefaults.outlinedTextFieldColors()
                        )
                    }
                )
            }
        )

        // https://issuetracker.google.com/u/1/issues/268294552
        errorMessage?.run {
            Text(
                text = this,
                modifier = Modifier
                    .padding(top = 2.dp, start = 16.dp)
                    .semantics {
                        liveRegion = LiveRegionMode.Polite
                    }
            )
        }

        if (inputType == InputType.DATE && errorMessage == null) {
            Text(
                text = DATE_HELPER_TEXT,
                style = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.onSurface.copy(ContentAlpha.disabled)),
                modifier = Modifier.padding(top = 2.dp, start = 16.dp)
            )
        }
    }
}

}

enum class InputType {
    TEXT,
    NUMBER,
    NUMBER_DECIMAL,
    DATE,
    PHONE,
    PASSWORD,
    EMAIL,
    CODE,
    USERNAME
}
