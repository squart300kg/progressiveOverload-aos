package com.example.program.repository

import com.example.program.model.entity.ExerciseTypeTable
import com.example.program.model.entity.ProgramTable
import com.example.program.model.entity.RecordTable
import com.example.program.model.model.RecordExerciseModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by sangyoon on 2021/07/27
 */
interface RoomRepository {

//    fun getFreeLectures(playListId: String, apiKey: String): Flow<YoutubeResponse>

    fun insertProgram(program : ProgramTable) : Flow<Long>

    fun insertExerciseType(exerciseTypeTable : ExerciseTypeTable) : Flow<Long>

    fun getAllProgram() : Flow<List<ProgramTable>>

    fun getTargetedProgram(targetName : String) : Flow<ProgramTable>

    fun getExercises(programNo: Long?, splitIndex: Int?) : Flow<List<ExerciseTypeTable>>

    fun getExperformedStatuses(programNo: Long?, exerciseNo: Long?): Flow<Int>

    fun deleteProgram(programNo: Long?) : Flow<Int>

    fun deleteExercise(exerciseTypeTable: ExerciseTypeTable?) : Flow<Int>

    fun updateProgramName(name: String, programNo: Long?) : Flow<Int>

    fun updateExercise(exerciseTypeTable: ExerciseTypeTable?): Flow<Int>

    fun insertRecord(model: RecordTable): Flow<Long>

}