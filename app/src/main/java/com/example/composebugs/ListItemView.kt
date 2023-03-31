package com.example.composebugs

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

const val LIST_ITEM_VIEW_TEST_TAG = "LIST_ITEM_VIEW_TEST_TAG"
const val LIST_ITEM_VIEW_RIGHT_ARROW_TEST_TAG = "LIST_ITEM_VIEW_RIGHT_ARROW_TEST_TAG"

@Composable
fun ListItemView(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    labelText: String? = null,
    bottomView: @Composable ColumnScope.() -> Unit = {},
    onClick: (() -> Unit?)? = null
) {
    Row(
        modifier = Modifier
            .clickable {
                onClick?.invoke()
            }
            .fillMaxWidth()
            .padding(16.dp)
            .testTag(LIST_ITEM_VIEW_TEST_TAG)
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxSize()
                .align(Alignment.CenterVertically)
        ) {
            labelText?.run {
                Text(
                    text = this,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp)
                )
            }

            Text(
                text = title,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp)
            )

            subtitle?.run {
                Text(
                    text = this,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp)
                )
            }

            bottomView.invoke(this)
        }

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .testTag(LIST_ITEM_VIEW_RIGHT_ARROW_TEST_TAG)
        )
    }
}
