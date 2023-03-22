@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.composebugs

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.example.composebugs.ui.theme.ComposeBugsTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBugsTheme {
                Scaffold {
                    Column(modifier = Modifier.padding(it)) {
                        var uno = remember { mutableStateOf(TextFieldValue("")) }
                        var dos = remember { mutableStateOf(TextFieldValue("")) }
                        var tres = remember { mutableStateOf(TextFieldValue("")) }

                        TextField(uno.value, onValueChange = {
                            uno.value = it
                        },
                            placeholder = {
                                Text("uno")
                            })
                        TextField(dos.value, onValueChange = {
                            dos.value = it
                        },
                            placeholder = {
                                Text("dos")
                            })
                        TextField(tres.value, onValueChange = {
                            tres.value = it
                        },
                            placeholder = {
                                Text("tres")
                            })
                        Button(onClick = { /*TODO*/ }) {
                            Text("Button")
                        }
                    }
                }
            }
        }
    }
}
