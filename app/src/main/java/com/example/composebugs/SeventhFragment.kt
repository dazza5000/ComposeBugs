@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.composebugs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.isContainer
import androidx.compose.ui.semantics.semantics
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
                        TopAppBar(title = {
                            Box {
                                Text("Title")
                            }
                        })
                    }
                ) { paddingValue ->
                    Box {
                        LazyColumn(
                            contentPadding = paddingValue,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            item {
                                Text("Text Field")
                            }
                            item {
                                Spacer(modifier = Modifier.height(8.dp))
                                ElevatedCard(
                                    onClick = {},
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                ) {
                                    Column() {
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Row(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .defaultMinSize(minHeight = 48.dp)
                                                .clickable { }
                                                .testTag("foo")
                                        ) {
                                            Row {
                                                Row {
                                                    Image(imageVector = Icons.Filled.Create, null,
                                                        modifier = Modifier
                                                        .padding(end = 12.dp)
                                                        .align(Alignment.CenterVertically)
                                                        .width(24.dp)
                                                        .height(24.dp)
                                                        .testTag("bar")
                                                    )

                                                    Text(
                                                        text = "0",

                                                        )
                                                }
                                                Text(
                                                    text = "Active streak",
                                                )
                                            }
                                            Row {
                                                Row {
                                                    Text(
                                                        text = "78"
                                                    )
                                                }
                                                Row {
                                                    Text(
                                                        text = "Total entries",
                                                        modifier = Modifier.semantics {
                                                            contentDescription = "content 1"
                                                        }
                                                    )
                                                }
                                            }

                                        }
                                        Row(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .defaultMinSize(minHeight = 48.dp)
                                                .clickable { }
                                                .testTag("foo")
                                        ) {
                                            Column {
                                                Text(
                                                    text = "0",
                                                    modifier = Modifier.semantics {
                                                        contentDescription = "content"
                                                    }
                                                )
                                                Text(
                                                    text = "Active streak"
                                                )
                                            }
                                            Column {
                                                Text(
                                                    text = "78",
                                                    modifier = Modifier.semantics {
                                                        contentDescription = "content"
                                                    }
                                                )
                                                Text(
                                                    text = "Total entries"
                                                )
                                            }
                                        }
                                        Spacer(modifier = Modifier.height(8.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
