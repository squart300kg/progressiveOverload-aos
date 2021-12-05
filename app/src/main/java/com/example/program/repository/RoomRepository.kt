package com.example.program.repository

import com.example.program.model.entity.ExerciseTypeTable
import com.example.program.model.entity.ProgramTable
import kotlinx.coroutines.flow.Flow

/**
 * Created by sangyoon on 2021/07/27
 */
interface RoomRepository {

//    fun getFreeLectures(playListId: String, apiKey: String): Flow<YoutubeResponse>

    fun insertProgram(program : ProgramTable) : Flow<Long>

    fun insertExerciseType(exerciseTypeTable : ExerciseTypeTable) : Flow<Long>

    fun getTargetedProgram(targetName : String) : Flow<ProgramTable>

    fun deleteProgram(programNo: Long?) : Flow<Int>

    fun getExercises(programNo: Long?, splitIndex: Int?) : Flow<List<ExerciseTypeTable>>
}