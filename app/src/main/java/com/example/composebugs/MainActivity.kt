@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.composebugs

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.lifecycleScope
import com.example.composebugs.ui.theme.ComposeBugsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val error = remember { mutableStateOf("") }
            val isLoading = remember { mutableStateOf(false) }
            val focusRequester = remember { FocusRequester() }
            var color by remember { mutableStateOf(Color.White) }

            LaunchedEffect(key1 = error.value, block = {
                if (error.value.isNotBlank()) {
                    focusRequester.requestFocus()
                }
            })

            ComposeBugsTheme {
                Scaffold {
                    Column(modifier = Modifier.padding(it)) {
                        LoadingDialog(showLoadingDialog = isLoading.value)
                        if (error.value.isNotBlank()) {
                            Text(
                                text = error.value, // This is the text that gets interrupted by clickable text
                                style = MaterialTheme.typography.bodySmall.copy(color = Color.Red),
                                modifier = Modifier
                                    .padding(top = 2.dp)
                                    .semantics(mergeDescendants = true) {
                                        liveRegion = LiveRegionMode.Assertive
                                    }
                                    .focusRequester(focusRequester)
                                    .onFocusChanged {
                                        color = if (it.isFocused) Color.Green else Color.White
                                    }
                                    .focusable(enabled = true)
                                    .focusTarget()
                                    .border(2.dp, color)
                            )
                        }
                        Text(
                            "End" // This text is required in order between error and clickable text.  Not sure why
                        )
                        Text(
                            "Clickable Text",
                            modifier = Modifier.clickable {

                                lifecycleScope.launch {
                                    isLoading.value = true

                                    delay(500L) // This delay allows time for focus to change to the dialog.  Removing or making it like 100 is basically the same as no dialog
                                    isLoading.value = false

                                    // delay(10000L) This actually fixes the issue but weird enough it takes like 5 seconds for this clickable text to get focus
                                    error.value = if (error.value.isBlank()) "Name is required" else ""


                                }
                            }

                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingDialog(showLoadingDialog: Boolean) {
    if (showLoadingDialog) {
        Dialog(
            onDismissRequest = { }
        ) {
            Text("Dialog") // If we remove this Text the issue does not appear.  I assume due to this dialog not getting talkback focus
        }
    }
}
