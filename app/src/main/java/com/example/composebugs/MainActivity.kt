package com.example.composebugs

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import com.example.composebugs.ui.theme.ComposeBugsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val softwareKeyboardController = LocalSoftwareKeyboardController.current


            ComposeBugsTheme {
                Scaffold {
                    Column(modifier = Modifier.padding(it)) {
                        var uno = remember { mutableStateOf(TextFieldValue("")) }
                        var dos = remember { mutableStateOf(TextFieldValue("")) }
                        var tres = remember { mutableStateOf(TextFieldValue("")) }

                        TextField(
                            uno.value, onValueChange = {
                                uno.value = it
                            },
                            placeholder = {
                                Text("uno")
                            },
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    softwareKeyboardController?.hide()
                                },
                            ),
                            singleLine = true
                        )
                        TextField(
                            dos.value, onValueChange = {
                                dos.value = it
                            },
                            placeholder = {
                                Text("dos")
                            },
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    softwareKeyboardController?.hide()
                                },
                            ),
                            singleLine = true
                        )
                        TextField(
                            tres.value, onValueChange = {
                                tres.value = it
                            },
                            placeholder = {
                                Text("tres")
                            },
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    softwareKeyboardController?.hide()
                                },
                            ),
                            singleLine = true
                        )
                        Button(onClick = { /*TODO*/ }) {
                            Text("Button")
                        }
                    }
                }
            }
        }
    }
}
