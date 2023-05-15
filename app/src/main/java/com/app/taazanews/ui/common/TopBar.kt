package com.app.taazanews.ui.common

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.app.taazanews.R

@Composable
fun TaazaTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        modifier = modifier,
        navigationIcon = {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "menu")
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
        actions = {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "search a news")
            }
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "search a news",
//                    tint = Color.Red
                )
            }
        }
    )
}


@Preview()
@Composable
fun PreviewTopBar() {
    TaazaTopBar()
}