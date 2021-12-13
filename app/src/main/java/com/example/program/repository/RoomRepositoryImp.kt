package com.example.program.repository

import androidx.annotation.WorkerThread
import com.example.program.api.ProgramDAO
import com.example.program.model.entity.ExerciseTypeTable
import com.example.program.model.entity.ProgramTable
import com.example.program.model.entity.RecordTable
import com.example.program.util.DateUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by sangyoon on 2021/07/27
 */
class RoomRepositoryImp(
    private val programDAO: ProgramDAO,
) : RoomRepository {

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
    override fun insertProgram(program: ProgramTable): Flow<Long> {
        return flow {
            val data = programDAO.insertProgram(program)
            emit(data)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun insertExerciseType(exerciseTypeTable: ExerciseTypeTable): Flow<Long> {
        return flow {
            val data = programDAO.insertExerciseType(exerciseTypeTable)
            emit(data)
        }
    }

    override fun getAllProgram(): Flow<List<ProgramTable>> {
        return flow {
            val data = programDAO.getAllProgram()
            emit(data)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun getTargetedProgram(targetName: String): Flow<ProgramTable> {
        return flow {
            val data = programDAO.getTargetedProgram(targetName)
            emit(data)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun deleteProgram(programNo: Long?): Flow<Int> {
        return flow {
            val data = programDAO.deleteProgram(programNo)
            emit(data)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun deleteExercise(exerciseTypeTable: ExerciseTypeTable?): Flow<Int> {
        return flow {
            val data = programDAO.deleteExercise(exerciseTypeTable)
            emit(data)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun getExercises(programNo: Long?, splitIndex: Int?): Flow<List<ExerciseTypeTable>> {
        return flow {
            val data = programDAO.getExercises(programNo, splitIndex)
            emit(data)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun getExercisePerformedStatuses(programNo: Long?, exerciseNo: Long?): Flow<Int> {
        return flow {
            val data = programDAO.getExperformedStatuses(programNo, exerciseNo)
            emit(data)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun getTodayExercisePerformed(programNo: Long?, exerciseNo: Long?): Flow<List<RecordTable>> {
        return flow {
            val data = programDAO.getTodayExercisePerformed(
                DateUtil.getCurrentDateForRecord(),
                programNo,
                exerciseNo)
            emit(data)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun updateProgramName(name: String, programNo: Long?): Flow<Int> {
        return flow {
            val data = programDAO.updateProgramName(name, programNo)
            emit(data)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun updateExercise(exerciseTypeTable: ExerciseTypeTable?): Flow<Int> {
        return flow {
            val data = programDAO.updateExercise(exerciseTypeTable)
            emit(data)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun insertRecord(model: RecordTable): Flow<Long> {
        return flow {
            val data = programDAO.insertRecord(model)
            emit(data)
        }
    }

}