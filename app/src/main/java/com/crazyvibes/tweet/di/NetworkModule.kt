package com.crazyvibes.tweet.di

import com.crazyvibes.tweet.api.TweetAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    //return retrofit object
    @Singleton
    @Provides
    fun providesRetrofit():Retrofit{
        return Retrofit.Builder().baseUrl("https://api.jsonbin.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesTweetAPI(retrofit: Retrofit):TweetAPI{
        return retrofit.create(TweetAPI::class.java)
    }
}