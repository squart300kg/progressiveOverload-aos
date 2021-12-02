package com.example.program.repository

import com.example.program.api.YoutubeApi
import com.example.program.model.response.YoutubeResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by sangyoon on 2021/07/27
 */
class YoutubeRepositoryImp(
    private val youtubeApi: YoutubeApi
): YoutubeRepository {

    override fun getFreeLectures(playListId: String, apiKey: String): Flow<YoutubeResponse> {
        return flow {
            val data = youtubeApi.getFreeLectures(
                playListId = playListId,
                key = apiKey)
            emit(data)
        }
    }

}