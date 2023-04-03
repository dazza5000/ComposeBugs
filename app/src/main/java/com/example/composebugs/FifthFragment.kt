package com.example.composebugs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

class FifthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Title") },
                            actions = {
                                TextButton(
                                    onClick = {
                                    }
                                ) {
                                    Image(
                                        painter = rememberVectorPainter(image = Icons.Default.Close),
                                        contentDescription = "Test"
                                    )
                                    Text(
                                        text = "Test"
                                    )
                                }
                            }
                        )
                    }
                ) { paddingValues ->
                    LazyColumn(
                        contentPadding = paddingValues,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        item {
                            Text("Android")
                        }
                        item {
                            Text("Android")
                        }
                        item {
                            Text("Android")
                        }
                        item {
                            Text("Android")
                        }
                    }
                }
            }
        }
    }
}
