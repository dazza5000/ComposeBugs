package com.example.composebugs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

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
                        TopAppBar(title = { Text("Title") })
                    }
                ) { paddingValue ->
                    HorizontalPager(
                        modifier = Modifier.padding(paddingValue),
                        count = 1,
                        state = rememberPagerState(0)
                    ) {
                        SwipeRefresh(
                            state = rememberSwipeRefreshState(isRefreshing = false),
                            onRefresh = { }
                        ) {
                            Text("Test")
                        }
                    }
                }
            }
        }
    }
}
