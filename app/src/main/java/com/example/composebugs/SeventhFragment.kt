package com.example.composebugs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment

class SeventhFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("Title") })
                    }
                ) { paddingValue ->
                    Row(
                        modifier = Modifier.padding(paddingValue),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Column(modifier = Modifier.background(Color.LightGray).padding(10.dp).weight(1f)) {
                            Text(
                                text = "0"
                            )
                            Text(
                                text = "Active streak"
                            )
                        }
                        Column(modifier = Modifier.background(Color.LightGray).padding(10.dp).weight(1f)) {
                            Text(
                                text = "78"
                            )
                            Text(
                                text = "Total entries"
                            )
                        }
                    }
                }
            }
        }
    }
}
