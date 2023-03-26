package com.app.taazanews.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.taazanews.ui.common.TaazaTopBar
import com.app.taazanews.ui.common.TabScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun NewsCategory(
    navController: NavHostController
) {
    val coroutine = rememberCoroutineScope()
    val tabs = listOf("All", "Business", "Education", "Entertainment", "Health", "Politics", "Sports", "Technology")
    //val selectedTabIndex by rememberSaveable { mutableStateOf(0) }
    val pagerState = rememberPagerState(0)

    Column {
        TaazaTopBar(canNavigateBack = false)
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 0.dp,
            backgroundColor = MaterialTheme.colors.primary,
            indicator = {tabPosition ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPosition[pagerState.currentPage])
                )

            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title, fontSize = 15.sp) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        // selectedTabIndex = index
                        coroutine.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }

        HorizontalPager(state = pagerState, count = tabs.size){
            when (pagerState.currentPage) {
                0 -> {
                    TabScreen(navController = navController, query = "india")
                }
                1 -> {
                    TabScreen(navController = navController, query = "business")
                }
                2 -> {
                    TabScreen(navController = navController, query = "education")
                }
                3 -> {
                    TabScreen(navController = navController, query = "entertainment")
                }
                4 -> {
                    TabScreen(navController = navController, query = "health")
                }
                5 -> {
                    TabScreen(navController = navController, query = "politics")
                }
                6 -> {
                    TabScreen(navController = navController, query = "sports")
                }
                7 -> {
                    TabScreen(navController = navController, query = "technology")
                }
            }
        }

        /*
        when (selectedTabIndex) {
            0 -> {
                TabScreen(navController = navController, query = "india")
            }
            1 -> {
                TabScreen(navController = navController, query = "business")
            }
            2 -> {
                TabScreen(navController = navController, query = "education")
            }
            3 -> {
                TabScreen(navController = navController, query = "entertainment")
            }
            4 -> {
                TabScreen(navController = navController, query = "health")
            }
            5 -> {
                TabScreen(navController = navController, query = "politics")
            }
            6 -> {
                TabScreen(navController = navController, query = "sports")
            }
            7 -> {
                TabScreen(navController = navController, query = "technology")
            }
        }
         */

    }
}