package com.example.composebugs

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.TextFieldValue
import com.example.composebugs.ui.theme.ComposeBugsTheme


class MainActivity : ComponentActivity() {

    private val WELL_TEXT_FIELD_TEST_TAG: String = "foo"

    @OptIn(ExperimentalComposeUiApi::class)
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val softwareKeyboardController = LocalSoftwareKeyboardController.current



            ComposeBugsTheme {
                Scaffold(
                    topBar = {
                        TopAppBar() {

                        }
                    },
                    bottomBar = {
                        Button(onClick = { /*TODO*/ }) {
                            Text("Button")
                        }
                    }) {

                    Column(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                            .testTag("bar")
                    ) {
                        var uno = remember { mutableStateOf(TextFieldValue("")) }
                        var dos = remember { mutableStateOf(TextFieldValue("")) }
                        var tres = remember { mutableStateOf(TextFieldValue("")) }
                        var cuatro = remember { mutableStateOf(TextFieldValue("")) }


                        Column(
                            modifier = Modifier.testTag("foobar")
                        ) {
                            ListItemView(title = "foo", subtitle = "bar", onClick = {})
                            ListItemView(title = "foo", subtitle = "bar", onClick = {})
                            ListItemView(title = "foo", subtitle = "bar", onClick = {})
                            ListItemView(title = "foo", subtitle = "bar", onClick = {})
                            ListItemView(title = "foo", subtitle = "bar", onClick = {})
                            ListItemView(title = "foo", subtitle = "bar", onClick = {})
                            ListItemView(title = "foo", subtitle = "bar", onClick = {})

                            TextField(
                                uno.value,
                                onValueChange = {
                                    uno.value = it
                                },
                                modifier = Modifier
                                    .testTag(WELL_TEXT_FIELD_TEST_TAG)
                                    .fillMaxWidth(),
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
                        }

                        TextField(
                            dos.value, onValueChange = {
                                dos.value = it
                            },
                            modifier = Modifier
                                .testTag(WELL_TEXT_FIELD_TEST_TAG)
                                .fillMaxWidth(),
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
                            modifier = Modifier
                                .testTag(WELL_TEXT_FIELD_TEST_TAG)
                                .fillMaxWidth(),
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


                        bogusField()
                        bogusField()

                        TextField(
                            cuatro.value,
                            onValueChange = {
                                cuatro.value = it
                            },
                            modifier = Modifier
                                .testTag(WELL_TEXT_FIELD_TEST_TAG)
                                .fillMaxWidth(),
                            placeholder = {
                                Text("cuatro")
                            },
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    softwareKeyboardController?.hide()
                                },
                            ),
                            singleLine = true
                        )

                        bogusField()
                        bogusField()
                        bogusField()
                        bogusField()
                        bogusField()
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun bogusField() {
    val softwareKeyboardController = LocalSoftwareKeyboardController.current
    var bogus = remember { mutableStateOf(TextFieldValue("")) }



    TextField(
        bogus.value, onValueChange = {
            bogus.value = it
        },
        modifier = Modifier
            .testTag("bogusTag")
            .fillMaxWidth(),
        placeholder = {
            Text("bogusField")
        },
        keyboardActions = KeyboardActions(
            onDone = {
                softwareKeyboardController?.hide()
            },
        ),
        singleLine = true
    )
}


