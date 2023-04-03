package com.example.composebugs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class ThirdFragment : Fragment() {

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
                            title = { Text("Title") }
                        )
                    }
                ) { paddingValues ->
                    LazyColumn(
                        contentPadding = paddingValues,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        item {
                            Button(
                                onClick = {
                                    findNavController().navigate(R.id.action_ThirdFragment_to_FourthFragment)
                                }
                            ) {
                                Text("Test")
                            }
                        }
                    }
                }
            }
//
//                val tabTitles = rememberSaveable {
//                    listOf(
//                        "Tab 1",
//                        "Tab 2"
//                    )
//                }
//                val coroutineScope = rememberCoroutineScope()
//                val pagerState = rememberPagerState(initialPage = 0)
//                ComposeBugsTheme {
//                    Scaffold(
//                        modifier = Modifier.testTag("Scaffold"),
//                        floatingActionButton = {
//                            FloatingActionButton(
//                                modifier = Modifier.testTag("Scaffold"),
//                                onClick = { }) {
//                                Icon(
//                                    modifier = Modifier.testTag("Scaffold"),
//                                    imageVector = Icons.Filled.Add,
//                                    contentDescription = "Add"
//                                )
//                            }
//                        },
//                        topBar = {
//                            Column {
//                                TopAppBar(
//                                    modifier = Modifier.testTag("Scaffold"),
//                                    title = {
//                                        Text(
//                                            "Top App Bar",
//                                            modifier = Modifier.testTag("Scaffold")
//                                                .semantics { heading() }
//                                        )
//                                    },
//                                    navigationIcon = {
//                                        IconButton(
//                                            onClick = { },
//                                            modifier = Modifier.testTag("Scaffold")
//                                        ) {
//                                            Icon(
//                                                imageVector = Icons.Filled.ArrowBack,
//                                                contentDescription = "Back",
//                                                tint = Color.White,
//                                                modifier = Modifier.testTag("Scaffold")
//                                            )
//                                        }
//                                    }
//                                )
//                                TabRow(
//                                    selectedTabIndex = pagerState.currentPage,
//                                    backgroundColor = MaterialTheme.colors.surface
//                                ) {
//                                    tabTitles.forEachIndexed { index, title ->
//                                        Tab(
//                                            selected = pagerState.currentPage == index,
//                                            onClick = {
//                                                coroutineScope.launch {
//                                                    pagerState.animateScrollToPage(index)
//                                                }
//                                            },
//                                            text = { Text(title) }
//                                        )
//                                    }
//                                }
//                            }
//                        },
//                        bottomBar = {
//                            BottomBar()
//                        }
//                    ) { paddingValues ->
//                        LazyColumn(modifier = Modifier.padding(paddingValues).testTag("Scaffold")) {
//                            item {
//                                Text("Android", modifier = Modifier.testTag("Scaffold"))
//                            }
//                            item {
//                                BoxScreen()
//                            }
//                        }
//                   }
        }
    }
}
