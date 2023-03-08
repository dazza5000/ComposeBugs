@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.composebugs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.composebugs.ui.theme.ComposeBugsTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val error = remember { mutableStateOf("") }
            val isLoading = remember { mutableStateOf(false) }
            val value = remember { mutableStateOf(TextFieldValue("")) }

            ComposeBugsTheme {
                Scaffold {
                    Column(modifier = Modifier.padding(it)) {
                        LoadingDialog(showLoadingDialog = isLoading.value)
                        TextField(
                            value = value.value,
                            label = { Text("Name") },
                            onValueChange = { textFieldValue ->
                                value.value = textFieldValue
                            }
                        )

                        if (error.value.isNotBlank()) {
                            Text(
                                text = error.value,
                                style = MaterialTheme.typography.bodySmall.copy(color = Color.Red),
                                modifier = Modifier
                                    .padding(top = 2.dp)
                                    .semantics(mergeDescendants = true) {
                                        liveRegion = LiveRegionMode.Polite
                                    }
                            )
                        }
                        if (value.value.text.isNotBlank()) {
                            Text(
                                text = "Only blank is allowed.  You have " + value.value.text,
                                style = MaterialTheme.typography.bodySmall.copy(color = Color.Red),
                                modifier = Modifier
                                    .padding(top = 2.dp)
                                    .semantics(mergeDescendants = true) {
                                        liveRegion = LiveRegionMode.Polite
                                    }
                            )
                        }
                        Button({
                            isLoading.value = true
                            GlobalScope.launch {
                                delay(500L)
                                error.value = if (error.value.isBlank()) "Name is required" else ""
                                isLoading.value = false
                            }
                        }) {
                            Text("Save")
                        }
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
            onDismissRequest = { },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(54.dp)
            ) {
                CircularProgressIndicator(
                    modifier =
                    Modifier
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(48.dp)
                        )
                        .padding(12.dp)
                        .fillMaxSize(),
                    strokeWidth = 3.dp
                )
            }
        }
    }
}
