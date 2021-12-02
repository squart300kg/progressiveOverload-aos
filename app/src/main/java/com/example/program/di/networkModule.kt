package com.example.program.di

import android.util.Log
import com.example.program.api.CommonApi
import com.example.program.api.YoutubeApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val TIMEOUT = 10L

fun debugInterceptor(apiType: String) = HttpLoggingInterceptor(object: HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Log.d("${apiType}_API", message)
    }

}).apply {
    level = HttpLoggingInterceptor.Level.BODY
}

val networkModule = module {
    factory <CommonApi> {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(debugInterceptor("Chatting"))
//            .addInterceptor(AuthInterceptor(get()))
            .build()

        val url = "https://us-central1-thewarofstars-8f9a8.cloudfunctions.net/"

        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CommonApi::class.java)
    }

    factory <YoutubeApi> {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(debugInterceptor("Chatting"))
//            .addInterceptor(AuthInterceptor(get()))
            .build()

        val url = "https://www.googleapis.com/youtube/v3/"

        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YoutubeApi::class.java)
    }
}