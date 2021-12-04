package com.example.program.api

import androidx.room.Dao
import androidx.room.Delete
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

    @Query("SELECT * FROM programtable")
    fun getAllProgram(): List<ProgramTable>

    @Insert
    fun insertProgram(vararg programs: ProgramTable)

    @Insert
    fun insertExerciseType(vararg exerciseType: ExerciseTypeTable)

    @Delete
    fun deleteOneProgram(program: ProgramTable)

    @Delete
    fun deleteExerciseType(exerciseTypeTable: ExerciseTypeTable)
}