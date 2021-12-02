package com.example.program.api

import com.example.program.model.response.YoutubeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

    @GET("playlistItems")
    suspend fun getFreeLectures(
        @Query("part")part: String = "snippet",
        @Query("fields")fields: String = "items(snippet(title, description, resourceId.videoId, thumbnails.high.url))",
        @Query("key")key: String,
        @Query("playlistId")playListId: String,
        @Query("maxResults")maxResults: String = "15",
    ): YoutubeResponse

}