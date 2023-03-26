package com.app.taazanews.ui


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.taazanews.BuildConfig
import com.app.taazanews.data.Article
import com.app.taazanews.data.NewsApi
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsViewModel : ViewModel() {
    private val BASE_URL = "https://newsapi.org/v2/"
    private val apiKey = BuildConfig.API_KEY // API_KEY is hidden
    private val country = "in"
    private val api: NewsApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApi::class.java)

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

//    init {
//        fetchNews(query = "technology")
//    }

    fun fetchNews(category: String?) {
        viewModelScope.launch {
            try {
                val response = api.getNews(country, category, apiKey)
                if (response.status == "ok") {
                    _articles.postValue(response.articles)
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching news", e)
            }
        }
    }

    companion object {
        const val TAG = "NewsViewModel"
    }
}
