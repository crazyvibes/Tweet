package com.crazyvibes.tweet.repository

import com.crazyvibes.tweet.api.TweetAPI
import com.crazyvibes.tweet.models.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Locale.Category
import javax.inject.Inject

class TweetRepository @Inject constructor(private val tweetAPI: TweetAPI) {

    private val _categories = MutableStateFlow<List<String>>(emptyList()) //not accessible, writable
    val categories: StateFlow<List<String>> //accessible, read only
        get() = _categories //getter properties

    private val _tweets = MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweets:StateFlow<List<TweetListItem>>
        get() = _tweets

    suspend fun getCategories(){
        val response = tweetAPI.getCategories("tweets..category")
        if (response.isSuccessful && response.body()!=null){
            _categories.emit(response.body()!!)
        }
    }

    suspend fun getTweets(category:String){
        val response = tweetAPI.getTweets("tweets[?(@.category==\"$category\")]")
        if (response.isSuccessful && response.body()!=null){
            _tweets.emit(response.body()!!)
        }
    }
}