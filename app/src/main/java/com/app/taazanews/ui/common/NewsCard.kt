package com.app.taazanews.ui.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.app.taazanews.R
import com.app.taazanews.data.Article
import com.app.taazanews.ui.Screens
import com.app.taazanews.ui.openTab

@Composable
fun CommonNewsCard(
    news: Article,
    navController: NavHostController
) {
    val context: Context = LocalContext.current
    val url = news.urlToImage.toString()
    var markedFavorite by remember {
        mutableStateOf(false)
    }
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
                openTab(context, news.url)
            },
        elevation = 6.dp
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
            if(painter.state is AsyncImagePainter.State.Success){
                Image(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .height(200.dp)
                        .fillMaxWidth(),
                    painter = painter,
                    contentScale = ContentScale.Crop,
                    contentDescription = stringResource(R.string.image_description)
                )
            }else{
                CircularProgressIndicator(modifier = Modifier, color = Color.Black)
            }

            Row(modifier = Modifier
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = news.description?:"",
                    modifier = Modifier.weight(8f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    onClick = {
                        markedFavorite = !markedFavorite
                        if(markedFavorite){
                            Toast.makeText(context, "Marked as favorites", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Icon(
                        imageVector = when(markedFavorite){
                            false -> {Icons.Filled.FavoriteBorder}
                            else -> {Icons.Filled.Favorite}
                        },
//                        tint = if (markedFavorite) Color.Red else MaterialTheme.colors.onSurface,
                        contentDescription = "mark as favorite news"
                    )
                }
                IconButton(
                    onClick = {
                              shareNews(news = news, context = context)
                    },
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = "share")
                }
            }
        }
    }
}

private fun shareNews(news: Article, context: Context) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, "${news.title}\n\n${news.description}\n\nRead the full article at ${news.url}")
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.app_name)
        )
    )
}

