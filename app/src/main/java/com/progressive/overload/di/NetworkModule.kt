package com.progressive.overload.di

import android.util.Log
import com.progressive.overload.api.CommonApi
import com.progressive.overload.api.YoutubeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    const val TIMEOUT = 10L

    @Provides
    fun debugInterceptor(apiType: String) = HttpLoggingInterceptor(object: HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Log.d("${apiType}_API", message)
        }

    }).apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(debugInterceptor("Chatting"))
            .build()
    }

    @Provides
    fun provideCommonApi(
        okHttpClient: OkHttpClient
    ) : CommonApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://us-central1-thewarofstars-8f9a8.cloudfunctions.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CommonApi::class.java)
    }

    @Provides
    fun provideYouTubeApi(
        okHttpClient: OkHttpClient
    ): YoutubeApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://www.googleapis.com/youtube/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YoutubeApi::class.java)
    }


}
