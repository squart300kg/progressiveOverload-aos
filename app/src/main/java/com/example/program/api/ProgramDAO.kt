package com.example.program.api

import androidx.room.*
import com.example.program.model.entity.ExerciseTypeTable
import com.example.program.model.entity.ProgramTable
import com.example.program.model.entity.RecordTable
import com.example.program.model.model.ExerciseVolumeModel
import com.example.program.model.model.RecordModel

/**
 * Created by sangyoon on 2021/07/27
 */

@Dao
interface ProgramDAO {

//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray): List<User>

//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): User

    //    @Delete
//    fun deleteExerciseType(exerciseTypeTable: ExerciseTypeTable)

    @Query("SELECT * FROM programtable ORDER BY `no` DESC")
    fun getAllProgram(): List<ProgramTable>

    @Query("SELECT * FROM programtable WHERE name == :targetName")
    fun getTargetedProgram(targetName: String): ProgramTable

    @Query("SELECT COUNT(*) FROM recordtable WHERE recordTime == :recordTime AND programNo == :programNo  AND exerciseTypeNo == :exerciseNo")
    fun getTodayExercisesPerformedStatuses(recordTime: String?, programNo: Long?, exerciseNo: Long?): Int

    @Query("SELECT * FROM recordtable WHERE recordTime == :recordTime AND programNo == :programNo AND exerciseTypeNo == :exerciseNo")
    fun getTodayExercisePerformed(recordTime: String?, programNo: Long?, exerciseNo: Long?): List<RecordTable>

    @Query("SELECT * FROM recordtable WHERE recordTime == :recordTime AND programNo == :programNo ORDER BY name ASC, setNum ASC")
    fun getTargetOneDayRecord(recordTime: String?, programNo: Long?): List<RecordTable>

    @Query("SELECT * FROM exercisetypetable WHERE programNo == :programNo AND splitTypeIndex == :splitIndex")
    fun getExercises(programNo: Long?, splitIndex: Int?): List<ExerciseTypeTable>

    @Query("SELECT recordTime, SUM(weight * repitition) AS totalVolume FROM recordtable WHERE programNo == :programNo  GROUP BY recordTime ORDER BY recordTime DESC")
    fun getAllRecordsDateByProgramNo(programNo: Long): List<RecordModel>

    @Query("SELECT * FROM recordtable WHERE programNo == :programNo AND recordTime == :recordTime")
    fun getTargetDateTotalVolume(recordTime: String?, programNo: Long?): Int

    @Query("SELECT name, SUM(weight * repitition) AS totalVolume FROM recordtable WHERE programNo == :programNo AND recordTime == :recordTime GROUP BY name ORDER BY null")
    fun getExerciseVolumes(programNo: Long, recordTime: String): List<ExerciseVolumeModel>

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
    fun updateExercise(vararg exerciseTypeTable: ExerciseTypeTable?): Int



}