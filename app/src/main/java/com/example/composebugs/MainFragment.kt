package com.example.composebugs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.composebugs.ui.theme.ComposeBugsTheme

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MainScreen(findNavController())
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    ComposeBugsTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Title") },
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
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp).clickable {
                            navController.navigate(R.id.action_MainFragment_to_FirstFragment)
                        },
                    text = "1st"
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp).clickable {
                            navController.navigate(R.id.action_MainFragment_to_ThirdFragment)
                        },
                    text = "3rd"
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp).clickable {
                            navController.navigate(R.id.action_MainFragment_to_FifthFragment)
                        },
                    text = "5th"
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp).clickable {
                            navController.navigate(R.id.action_MainFragment_to_SixthFragment)
                        },
                    text = "6th"
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp).clickable {
                            navController.navigate(R.id.action_MainFragment_to_SeventhFragment)
                        },
                    text = "7th"
                )
            }
        }
    }
}
