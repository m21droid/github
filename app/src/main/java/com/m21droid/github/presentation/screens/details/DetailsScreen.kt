package com.m21droid.github.presentation.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.m21droid.github.R
import com.m21droid.github.presentation.Const.ERROR
import com.m21droid.github.presentation.previews.DetailsPreview
import com.m21droid.github.presentation.views.AppTopAppBar
import com.m21droid.github.presentation.views.MainText

@Preview
@Composable
fun DetailsScreenPreview1() {
    val state = remember { mutableStateOf(DetailsState.Empty) }
    DetailsScreen(state = state)
}

@Preview
@Composable
fun DetailsScreenPreview2(@PreviewParameter(DetailsPreview::class) data: DetailsStateData) {
    val state = remember { mutableStateOf(DetailsState.Display(data)) }
    DetailsScreen(state = state)
}

@Composable
fun DetailsScreen(
    state: State<DetailsState>,
    onClickBack: (DetailsStateData?) -> Unit = {},
) {
    val value = state.value
    Scaffold(
        topBar = {
            AppTopAppBar(
                title = {
                    Text(text = "Деталі користувача")
                },
                navigationIcon = {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            val data = (state.value as? DetailsState.Display)?.data
                            onClickBack(data)
                        }
                    )
                },
                actions = {
                    if (value is DetailsState.Display) {
                        val selected = value.data.second
                        Icon(
                            Icons.Filled.Star,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                selected.value = !selected.value
                            },
                            tint = if (selected.value) Yellow else White
                        )
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            val image = ImageVector.vectorResource(id = R.drawable.ic_account_circle_128)
            when (value) {
                DetailsState.Loading -> {
                    CircularProgressIndicator()
                }

                DetailsState.Failure -> {
                    MainText(text = ERROR)
                }

                DetailsState.Empty -> {
                    Image(imageVector = image, contentDescription = null)
                }

                is DetailsState.Display -> {
                    val details = value.data.first
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.weight(1.0f))
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(details.avatarUrl)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier
                                .size(128.dp)
                                .clip(CircleShape),
                            placeholder = rememberVectorPainter(image = image),
                            error = rememberVectorPainter(image = image),
                            contentScale = ContentScale.Fit,
                        )
                        MainText(text = details.login, fontWeight = FontWeight.Bold)
                        MainText(text = details.name)
                        MainText(text = details.location)
                        Spacer(modifier = Modifier.weight(10.0f))
                    }
                }
            }
        }
    }
}