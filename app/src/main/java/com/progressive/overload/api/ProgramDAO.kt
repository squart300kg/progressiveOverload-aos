package com.progressive.overload.api

import androidx.room.*
import com.progressive.overload.model.entity.ExerciseTypeTable
import com.progressive.overload.model.entity.ProgramTable
import com.progressive.overload.model.entity.RecordTable
import com.progressive.overload.model.model.ExerciseVolumeModel
import com.progressive.overload.model.model.RecordModel

/**
 * Created by sangyoon on 2021/07/27
 */

@Dao
interface ProgramDAO {

    @Query("SELECT * FROM programtable ORDER BY `no` DESC")
    fun getAllProgram(): List<ProgramTable>

    @Query("SELECT * FROM programtable WHERE name == :targetName")
    fun getTargetedProgram(targetName: String): ProgramTable

    @Query("SELECT COUNT(*) FROM recordtable WHERE programNo == :programNo  AND exerciseTypeNo == :exerciseNo")
    fun getPerformedSets(
//        recordTime: String?,
        programNo: Long?,
        exerciseNo: Long?,
    ): Int

    @Query("SELECT * FROM recordtable WHERE recordTime == :recordTime AND programNo == :programNo AND exerciseTypeNo == :exerciseNo")
    fun getTargetedExercisePerformed(
        recordTime: String?,
        programNo: Long?,
        exerciseNo: Long?,
    ): List<RecordTable>

    @Query("SELECT * FROM recordtable WHERE programNo == :programNo AND exerciseTypeNo == :exerciseNo")
    fun getTargetedExercisePerformed(
        programNo: Long?,
        exerciseNo: Long?,
    ): List<RecordTable>

    @Query("SELECT DISTINCT(recordTime) FROM recordtable WHERE programNo == :programNo AND exerciseTypeNo == :exerciseNo ORDER BY recordTime DESC")
    fun getTargetedAllDate(programNo: Long?, exerciseNo: Long?): List<String>

    @Query("SELECT MAX(recordTime) FROM recordtable WHERE  programNo == :targetedProgramNo AND exerciseTypeNo == :targetedExerciseNo AND recordTime < :targetedDate")
    fun getPreviousDate(targetedProgramNo: Long?, targetedExerciseNo: Long?, targetedDate: String?): String

    @Query("SELECT MAX(recordTime) FROM recordtable WHERE  programNo == :targetedProgramNo AND recordTime < :targetedDate")
    fun getPreviousDate(targetedProgramNo: Long?, targetedDate: String?): String

    @Query("SELECT MIN(recordTime) FROM recordtable WHERE  programNo == :targetedProgramNo AND exerciseTypeNo == :targetedExerciseNo AND recordTime > :targetedDate")
    fun getNextDate(targetedProgramNo: Long?, targetedExerciseNo: Long?, targetedDate: String?): String

    @Query("SELECT MIN(recordTime) FROM recordtable WHERE  programNo == :targetedProgramNo AND recordTime > :targetedDate")
    fun getNextDate(targetedProgramNo: Long?, targetedDate: String?): String

    @Query("SELECT * FROM recordtable WHERE name == :name AND recordTime == :recordTime AND programNo == :programNo ORDER BY name ASC, setNum ASC")
    fun getTargetOneDayRecord(
        name: String?,
        recordTime: String?,
        programNo: Long?,
    ): List<RecordTable>

    @Query("SELECT name FROM RecordTable WHERE recordTime == :recordTime AND programNo == :programNo GROUP BY name ORDER BY name ASC")
    fun getOneDayRecordName(recordTime: String?, programNo: Long?): List<String>

    @Query("SELECT * FROM ExerciseTypeTable WHERE programNo == :programNo AND mesoCycleSplitIndex == :mesoCycleSplitIndex AND microCycleSplitIndex == :microCycleSplitIndex")
    fun getExercises(programNo: Long, mesoCycleSplitIndex: Int, microCycleSplitIndex: Int): List<ExerciseTypeTable>

    @Query("SELECT * FROM ExerciseTypeTable WHERE programNo == :programNo")
    fun getExercises(programNo: Long): List<ExerciseTypeTable>

    @Query("SELECT recordTime, SUM(weight * repitition) AS totalVolume FROM recordtable WHERE programNo == :programNo  GROUP BY recordTime ORDER BY recordTime DESC")
    fun getAllRecordsDateByProgramNo(programNo: Long): List<RecordModel>

    @Query("SELECT * FROM RecordTable WHERE programNo == :programNo AND recordTime == :recordTime")
    fun getTargetDateTotalVolume(recordTime: String?, programNo: Long?): Int

    @Query("SELECT name, SUM(weight * repitition) AS totalVolume FROM RecordTable WHERE programNo == :programNo AND recordTime == :recordTime GROUP BY name ORDER BY null")
    fun getExerciseVolumes(programNo: Long, recordTime: String): List<ExerciseVolumeModel>

    @Insert
    fun insertDummyPrograms(programs: ProgramTable): Long

    @Insert
    fun insertDummyExercises(exercises: ExerciseTypeTable): Long

    @Insert
    fun insertProgram(programs: ProgramTable): Long

    @Insert
    fun insertExerciseType(exerciseType: ExerciseTypeTable): Long

    @Insert
    fun insertRecord(recordTable: RecordTable): Long

    @Query("DELETE FROM programtable WHERE `no` == :programNo")
    fun deleteProgram(programNo: Long?): Int

    @Delete
    fun deleteExercise(vararg exerciseTypeTable: ExerciseTypeTable?): Int

    @Query("UPDATE programtable SET name = :name WHERE `no` == :programNo")
    fun updateProgramName(name: String, programNo: Long?): Int

    @Update
    fun updateExercise(vararg exerciseTypeTable: ExerciseTypeTable): Int

    @Query("INSERT INTO ProgramTable(name, mesoSplitCount, mesoSplitText, microCycleCount, microCycleText) SELECT name, mesoSplitCount, mesoSplitText, microCycleCount, microCycleText FROM ProgramTable WHERE `no` == :programNo")
    fun duplicateProgram(programNo: Long): Long

    @Query("INSERT INTO ExerciseTypeTable(name, weight, repitition, setNum, restTime, rpe, programNo, mesoCycleSplitIndex, microCycleSplitIndex) SELECT name, weight, repitition, setNum, restTime, rpe, programNo, mesoCycleSplitIndex, microCycleSplitIndex FROM ExerciseTypeTable WHERE `programNo` == :originProgramNo")
    fun duplicateExercises(originProgramNo: Long): Long

    @Transaction
    fun duplicateProgram(originProgramNo: Long, programName: String): String {
        // 1. 프로그램 복사
        val duplicateProgramNo = duplicateProgram(originProgramNo)

        // 2. 복사된 프로그램 제목 변경
        updateProgramName(programName, duplicateProgramNo)

        // 2. 복사된 프로그램이 가지고 있는 운동 종목들 복사
        val exercises = getExercises(originProgramNo)
        exercises.forEach {
            insertExerciseType(ExerciseTypeTable(
                name = it.name,
                weight = it.weight,
                repitition = it.repitition,
                setNum = it.setNum,
                restTime = it.restTime,
                rpe = it.rpe,
                programNo = duplicateProgramNo,
                mesoCycleSplitIndex = it.mesoCycleSplitIndex,
                microCycleSplitIndex = it.microCycleSplitIndex
            ))
        }
        return "duplicateSuccess"
    }


}