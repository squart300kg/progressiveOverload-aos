package com.example.program.api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.program.model.entity.ExerciseTypeTable
import com.example.program.model.entity.ProgramTable

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

    @Query("SELECT * FROM programtable")
    fun getAllProgram(): List<ProgramTable>

    @Query("SELECT * FROM programtable WHERE name == :targetName")
    fun getTargetedProgram(targetName : String) : ProgramTable

    @Insert
    fun insertProgram(programs: ProgramTable) : Long

    @Insert
    fun insertExerciseType(exerciseType: ExerciseTypeTable) : Long

    @Query("DELETE FROM programtable WHERE `no` == :programNo")
    fun deleteProgram(programNo: Long?) : Int

    @Query("SELECT * FROM exercisetypetable WHERE programNo == :programNo AND splitTypeIndex == :splitIndex")
    fun getExercises(programNo: Long?, splitIndex: Int?): List<ExerciseTypeTable>
}