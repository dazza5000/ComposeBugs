package com.example.composebugs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.semantics.CollectionInfo
import androidx.compose.ui.semantics.collectionInfo
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment


class SixthFragment : Fragment() {

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
                            title = { androidx.compose.material3.Text("Title") }
                        )
                    }
                ) { paddingValues ->
                    LazyColumn(
                        contentPadding = paddingValues,
                        modifier = Modifier
                            .fillMaxWidth(),
                        state = rememberLazyListState()
                    ) {
                        item {
                            Text("foo")
                        }

                        item {
                        Text("bar")

                        }

                        item {
                            ElevatedCard(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                content = {


                                    Row(
                                        modifier = Modifier.semantics(mergeDescendants = true) {

                                        },
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        Image(
                                            imageVector = Icons.Filled.Create,
                                            contentDescription = "foo",
                                            alignment = Alignment.CenterStart,
                                        )

                                        androidx.compose.material3.Text(
                                            "Beginning Text"
                                        )

                                        Row(
                                            modifier = Modifier.semantics {
                                                contentDescription = "content description"
                                            },
                                            horizontalArrangement = Arrangement.End
                                        ) {
                                            androidx.compose.material3.Text(
                                                text = "End Text",
                                            )
                                        }
                                    }
                                    Row(
                                        modifier = Modifier.semantics(mergeDescendants = true) {

                                        },
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        Image(
                                            imageVector = Icons.Filled.Create,
                                            contentDescription = "foo",
                                            alignment = Alignment.CenterStart,
                                        )

                                        androidx.compose.material3.Text(
                                            "Beginning Text"
                                        )

                                        Row(
                                            modifier = Modifier.semantics {
                                                contentDescription = "content description 2"
                                            },
                                            horizontalArrangement = Arrangement.End
                                        ) {
                                            androidx.compose.material3.Text(
                                                text = "End Text",
                                            )
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
