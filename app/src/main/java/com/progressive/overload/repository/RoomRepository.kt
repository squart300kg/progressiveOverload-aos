package com.progressive.overload.repository

import com.progressive.overload.model.entity.ExerciseTypeTable
import com.progressive.overload.model.entity.ProgramTable
import com.progressive.overload.model.entity.RecordTable
import com.progressive.overload.model.model.ExerciseTypeModel
import com.progressive.overload.model.model.ExerciseVolumeModel
import com.progressive.overload.model.model.RecordModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by sangyoon on 2021/07/27
 */
interface RoomRepository {

//    fun getFreeLectures(playListId: String, apiKey: String): Flow<YoutubeResponse>

    fun insertProgram(program: ProgramTable): Flow<Long>

    fun insertExerciseType(exerciseTypeTable: ExerciseTypeTable): Flow<Long>

    fun insertRecord(model: RecordTable): Flow<Long>

    fun getAllProgram(): Flow<List<ProgramTable>>

    fun getTargetedProgram(targetName: String): Flow<ProgramTable>

    fun getExercises(programNo: Long, mesoCycleSplitIndex: Int, microCycleSplitIndex: Int): Flow<List<ExerciseTypeModel>>

    fun getPerformedSets(programNo: Long?, exerciseNo: Long?): Flow<Int>

    fun getTargetedExercisePerformed(programNo: Long?, exerciseNo: Long?, targetedDate: String?): Flow<List<RecordTable>>

    fun getTargetedExercisePerformed(programNo: Long, exerciseNo: Long): Flow<List<RecordTable>>

    fun getAllRecordsDateByProgramNo(programNo: Long): Flow<List<RecordModel>>

    fun getTargetDateTotalVolume(programNo: Long?, recordTime: String?): Flow<Int>

    fun getExerciseVolumes(programNo: Long, recordTime: String): Flow<List<ExerciseVolumeModel>>

    fun getOneDayRecord(name : String?, programNo: Long?, recordTime: String?): Flow<List<RecordTable>>

    fun getOneDayRecordName(programNo: Long?, recordTime: String?): Flow<List<String>>

    fun deleteProgram(programNo: Long?): Flow<Int>

    fun deleteExercise(exerciseTypeModel: ExerciseTypeModel?): Flow<Int>

    fun updateProgramName(name: String, programNo: Long?): Flow<Int>

    fun updateExercise(exerciseTypeTable: ExerciseTypeTable): Flow<Int>

    fun getTargetedAllDate(programNo: Long?, exerciseNo: Long?): Flow<List<String>>

    fun getPreviousDate(programNo: Long?, targetedExerciseNo: Long?, targetedDate: String?): Flow<String>

    fun getPreviousDate(programNo: Long?, targetedDate: String?): Flow<String>

    fun getNextDate(targetedProgramNo: Long?, targetedExerciseNo: Long?, targetedDate: String?): Flow<String>

    fun getNextDate(targetedProgramNo: Long?, targetedDate: String?): Flow<String>

    fun duplicateProgram(programNo: Long, name: String): Flow<String>


}