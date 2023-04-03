@file:OptIn(ExperimentalFoundationApi::class)

package com.example.composebugs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Scaffold(
                    topBar = {
                        Column {
                            TopAppBar(
                                title = { Text("Well Design System") },
                                navigationIcon = {
                                    IconButton(
                                        onClick = {
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Close"
                                        )
                                    }
                                }
                            )
                        }
                    },
                    content = { padding ->
                        Box(modifier = Modifier.padding(padding)) {
                        }
                    }
                )
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
}

@Composable
private fun BottomBar() {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(modifier = Modifier.testTag("Scaffold")) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    "",
                    modifier = Modifier.testTag("Scaffold")
                )
            },
            label = { Text(text = "Home", modifier = Modifier.testTag("Scaffold")) },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0
            },
            modifier = Modifier.testTag("Scaffold")
        )

        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    "",
                    modifier = Modifier.testTag("Scaffold")
                )
            },
            label = { Text(text = "Favorite") },
            selected = (selectedIndex.value == 1),
            onClick = {
                selectedIndex.value = 1
            },
            modifier = Modifier.testTag("Scaffold")
        )

        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    "",
                    modifier = Modifier.testTag("Scaffold")
                )
            },
            label = { Text(text = "Profile") },
            selected = (selectedIndex.value == 2),
            onClick = {
                selectedIndex.value = 2
            },
            modifier = Modifier.testTag("Scaffold")
        )
    }
}

@Composable
private fun BoxScreen() {
    Row(
        modifier = Modifier.padding(20.dp).testTag("Scaffold"),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Column(
            modifier = Modifier.background(Color.LightGray).padding(10.dp).weight(1f)
                .testTag("Scaffold")
        ) {
            Text(
                modifier = Modifier.testTag("Scaffold"),
                text = "0"
            )
            Text(
                text = "Active streak",
                modifier = Modifier.testTag("Scaffold")
            )
        }
        Column(
            modifier = Modifier.background(Color.LightGray).padding(10.dp).weight(1f)
                .testTag("Scaffold")
        ) {
            Text(
                text = "78",
                modifier = Modifier.testTag("Scaffold")
            )
            Text(
                text = "Total entries",
                modifier = Modifier.testTag("Scaffold")
            )
        }
    }
}
