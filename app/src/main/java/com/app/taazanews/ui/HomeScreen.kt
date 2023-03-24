package com.app.taazanews.ui

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.app.taazanews.R
import com.app.taazanews.data.Article
import com.app.taazanews.ui.common.TaazaTopBar


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    newsViewModel: NewsViewModel = viewModel(),
    navController: NavHostController
) {
    val newsArticles by newsViewModel.articles.observeAsState()

    Scaffold(
        topBar = {
            TaazaTopBar(canNavigateBack = navController.previousBackStackEntry!=null)
        }
    ) {
        if (newsArticles == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier, color = Color.Black)
                    Spacer(modifier = modifier.padding(vertical = 8.dp))
                    Text(text = stringResource(R.string.loading), fontSize = 16.sp)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .background(color = MaterialTheme.colors.primary)
            ) {
                items(newsArticles?: emptyList()) { article ->
                    NewsCard(news = article, navController = navController)
                }
            }
        }
    }
}

@Composable
fun NewsCard(
    news: Article,
    navController: NavHostController
) {

    val url = news.urlToImage.toString()
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .size(1080)
            .build()
    )

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    Screens.NewsScreen.route.replace(
                        oldValue = "{name}",
                        newValue = Uri.encode(news.url)
                    )
                )
            },
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = news.title,
                fontSize = 20.sp,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.h2
            )
            Image(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(8.dp)),
                painter = painter,
                contentDescription = stringResource(R.string.image_description)
            )
            Text(text = news.description!!)
        }
    }
}

