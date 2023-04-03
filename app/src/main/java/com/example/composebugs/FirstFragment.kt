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
import androidx.navigation.fragment.findNavController
import com.example.composebugs.ui.theme.ComposeBugsTheme

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                FirstScreen(findNavController())
            }
        }
    }
}

@Composable
fun FirstScreen(navController: NavController) {
    ComposeBugsTheme {
        Scaffold(
            topBar = {
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
                        .padding(16.dp),
                    text = "title"
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp).clickable {
                            navController.navigate(com.example.composebugs.R.id.action_FirstFragment_to_SecondFragment)
                        },
                    text = "title"
                )
            }
        }
    }
//
//        Scaffold(
//            topBar = {
//                TopAppBar(title = {
//                    Text(
//                        text = "Back Button Focus Issue",
//                        maxLines = 1,
//                        overflow = TextOverflow.Ellipsis,
//                        modifier = Modifier.semantics { heading() }
//                    )
//                })
//            }
//
//        ) { paddingValues ->
//            Box(modifier = Modifier.padding(paddingValues)) {
//                Box(modifier = Modifier.padding(20.dp)) {
//                    Button(
//                        onClick = { navController.navigate(R.id.action_FirstFragment_to_SecondFragment) },
//                        modifier = Modifier.fillMaxWidth()
//                    ) {
//                        Text(text = "Next Screen")
//                    }
//                }
//            }
//        }
//    }
}
