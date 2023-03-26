package com.app.taazanews.ui.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.app.taazanews.R
import com.app.taazanews.ui.NewsViewModel

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun TabScreen(
    modifier: Modifier = Modifier,
    newsViewModel: NewsViewModel = viewModel(),
    navController: NavHostController,
    query: String?
) {
    val newsArticles by newsViewModel.articles.observeAsState()
    val connectivityManager = LocalContext.current.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


    LaunchedEffect(Unit) {
        newsViewModel.fetchNews(query)
    }
    if (connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_INTERNET)== true){
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
        }else if (newsArticles!!.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(R.string.error_loading), fontSize = 16.sp)
            }
        }  else {
            LazyColumn(
                modifier = Modifier
                    .background(color = MaterialTheme.colors.primary)
            ) {
                items(newsArticles?: emptyList()) { article ->
                    CommonNewsCard(news = article, navController = navController)
                }
            }
        }
    }
    else{
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = Icons.Filled.Warning, contentDescription = stringResource(R.string.no_internet))
            Text(text = stringResource(R.string.no_internet), fontSize = 16.sp)
        }
    }
}
