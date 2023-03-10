@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.composebugs

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.lifecycleScope
import com.example.composebugs.ui.theme.ComposeBugsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val error = remember { mutableStateOf("") }
            val isLoading = remember { mutableStateOf(false) }
            val focusRequester = remember { FocusRequester() }
            var color by remember { mutableStateOf(Color.White) }

//            LaunchedEffect(key1 = error.value, block = {
//                if (error.value.isNotBlank()) {
//                    focusRequester.requestFocus()
//                }
//            })

            ComposeBugsTheme {
                Scaffold {
                    androidx.compose.animation.AnimatedVisibility(
                        visible = isLoading.value,
                        enter = fadeIn(),
                        exit = fadeOut(),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .background(Color.DarkGray),
                        ) {
                            NiaOverlayLoadingWheel(
                                modifier = Modifier
                                    .align(Alignment.Center),
                                contentDesc = "foo",
                            )
                        }
                    }
                    Column(modifier = Modifier.padding(it)) {
//                        LoadingDialog(showLoadingDialog = isLoading.value)

                        if (error.value.isNotBlank()) {
                            Text(
                                text = error.value, // This is the text that gets interrupted by clickable text
                                style = MaterialTheme.typography.bodySmall.copy(color = Color.Red),
                                modifier = Modifier
                                    .padding(top = 2.dp)
                                    .semantics(mergeDescendants = true) {
                                        liveRegion = LiveRegionMode.Assertive
                                    }
//                                    .focusRequester(focusRequester)
//                                    .onFocusChanged {
//                                        color = if (it.isFocused) Color.Green else Color.White
//                                    }
//                                    .focusable(enabled = true)
//                                    .focusTarget()
//                                    .border(2.dp, color)
                            )
                        }
                        Text(
                            "End" // This text is required in order between error and clickable text.  Not sure why
                        )
                        Text(
                            "Clickable Text",
                            modifier = Modifier.clickable {

                                lifecycleScope.launch {
                                    isLoading.value = true

                                    delay(500L) // This delay allows time for focus to change to the dialog.  Removing or making it like 100 is basically the same as no dialog
                                    isLoading.value = false

                                    // delay(10000L) This actually fixes the issue but weird enough it takes like 5 seconds for this clickable text to get focus
                                    error.value = if (error.value.isBlank()) "Name is required" else ""


                                }
                            }

                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingDialog(showLoadingDialog: Boolean) {
    if (showLoadingDialog) {
        Dialog(
            onDismissRequest = { }
        ) {
            Text("Dialog") // If we remove this Text the issue does not appear.  I assume due to this dialog not getting talkback focus
        }
    }
}

@Composable
fun NiaLoadingWheel(
    contentDesc: String,
    modifier: Modifier = Modifier,
) {
    val infiniteTransition = rememberInfiniteTransition()

    // Specifies the float animation for slowly drawing out the lines on entering
    val startValue = if (LocalInspectionMode.current) 0F else 1F
    val floatAnimValues = (0 until 7).map { remember { Animatable(startValue) } }
    LaunchedEffect(floatAnimValues) {
        (0 until 7).map { index ->
            launch {
                floatAnimValues[index].animateTo(
                    targetValue = 0F,
                    animationSpec = tween(
                        durationMillis = 100,
                        easing = FastOutSlowInEasing,
                        delayMillis = 40 * index,
                    ),
                )
            }
        }
    }

    // Specifies the rotation animation of the entire Canvas composable
    val rotationAnim by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 300, easing = LinearEasing),
        ),
    )

    // Specifies the color animation for the base-to-progress line color change

    // Draws out the LoadingWheel Canvas composable and sets the animations
    Canvas(
        modifier = modifier
            .size(48.dp)
            .padding(8.dp)
            .graphicsLayer { rotationZ = rotationAnim }
            .semantics { contentDescription = contentDesc }
            .testTag("loadingWheel"),
    ) {
        repeat(7) { index ->
            rotate(degrees = index * 30f) {
                drawLine(
                    color = Color.Black,
                    // Animates the initially drawn 1 pixel alpha from 0 to 1
                    alpha = if (floatAnimValues[index].value < 1f) 1f else 0f,
                    strokeWidth = 4F,
                    cap = StrokeCap.Round,
                    start = Offset(size.width / 2, size.height / 4),
                    end = Offset(size.width / 2, floatAnimValues[index].value * size.height / 4),
                )
            }
        }
    }
}

@Composable
fun NiaOverlayLoadingWheel(
    contentDesc: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = RoundedCornerShape(60.dp),
        shadowElevation = 8.dp,
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.83f),
        modifier = modifier
            .size(60.dp),
    ) {
        NiaLoadingWheel(
            contentDesc = contentDesc,
        )
    }
}


