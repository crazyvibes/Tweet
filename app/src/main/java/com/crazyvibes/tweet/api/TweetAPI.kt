package com.crazyvibes.tweet.api

import com.crazyvibes.tweet.models.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetAPI {
    @GET("v3/b/64b3dd858e4aa6225ebf1315?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category: String) :Response<List<TweetListItem>>


    @GET("v3/b/64b3dd858e4aa6225ebf1315?meta=false")
    suspend fun getCategories(@Header("X-JSON-Path") name: String) :Response<List<String>>
}