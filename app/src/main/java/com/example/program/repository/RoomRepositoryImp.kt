package com.example.program.repository

import androidx.annotation.WorkerThread
import com.example.program.api.ProgramDAO
import com.example.program.api.YoutubeApi
import com.example.program.model.entity.ExerciseTypeTable
import com.example.program.model.entity.ProgramTable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by sangyoon on 2021/07/27
 */
class RoomRepositoryImp(
    private val programDAO: ProgramDAO
): RoomRepository {

//    override fun getFreeLectures(playListId: String, apiKey: String): Flow<YoutubeResponse> {
//        return flow {
//            val data = youtubeApi.getFreeLectures(
//                playListId = playListId,
//                key = apiKey)
//            emit(data)
//        }
//    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun insertProgram(program: ProgramTable) {
        programDAO.insertProgram(program)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun insertExerciseType(exerciseTypeTable: ExerciseTypeTable) {
        programDAO.insertExerciseType(exerciseTypeTable)
    }

}