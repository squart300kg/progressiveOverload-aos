package com.progressive.overload.repository

import com.progressive.overload.model.response.YoutubeResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by sangyoon on 2021/07/27
 */
interface YoutubeRepository {

    fun getFreeLectures(playListId: String, apiKey: String): Flow<YoutubeResponse>

}