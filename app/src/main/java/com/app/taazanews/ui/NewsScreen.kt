package com.app.taazanews.ui

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.webkit.*
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.app.taazanews.ui.common.TaazaTopBar

@Composable
fun NewsDetailScreen(
    navController: NavHostController,
    mUrl: String
) {
    Scaffold(
        topBar = {
            TaazaTopBar()
        }
    ) {
        MyContent(modifier = Modifier.padding(it), mUrl = mUrl)
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun MyContent(
    modifier: Modifier = Modifier,
    mUrl: String
){
    AndroidView(
        factory = {
            WebView(it).apply {
                CookieManager.getInstance().setAcceptCookie(true)
                webViewClient = AdBlockWebViewClient()
                loadUrl(mUrl)
            }
        },
        update = {
            it.loadUrl(mUrl)
            it.settings.javaScriptEnabled = true
            it.reload()
        }
    )
}

fun openTab(context: Context, URL: String) {
    val builder = CustomTabsIntent.Builder()
    builder.setShowTitle(true)
    builder.setInstantAppsEnabled(true)
    val customBuilder = builder.build()
    customBuilder.launchUrl(context, Uri.parse(URL))
}

private class AdBlockWebViewClient : WebViewClient() {
    private val adBlockRegex = Regex("(.*)(doubleclick|adsystem|googlesyndication|adserver)(.*)")
    override fun shouldInterceptRequest(
        view: WebView?,
        request: WebResourceRequest?
    ): WebResourceResponse? {
        val url = request?.url.toString()
        return if (url.matches(adBlockRegex)) {
            WebResourceResponse("text/plain", "utf-8", null)
        }
        else {
            super.shouldInterceptRequest(view, request)
        }
    }
}