package com.example.program.repository

import androidx.annotation.WorkerThread
import com.example.program.api.ProgramDAO
import com.example.program.model.entity.ExerciseTypeTable
import com.example.program.model.entity.ProgramTable
import com.example.program.model.entity.RecordTable
import com.example.program.model.model.ExerciseTypeModel
import com.example.program.model.model.ExerciseVolumeModel
import com.example.program.model.model.RecordModel
import com.example.program.util.DateUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by sangyoon on 2021/07/27
 */
class RoomRepositoryImp(
    private val programDAO: ProgramDAO,
) : RoomRepository {

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
    override fun deleteExercise(model: ExerciseTypeModel?): Flow<Int> {
        return flow {
            val data = programDAO.deleteExercise(
                ExerciseTypeTable(
                    no = model!!.no,
                    name = model.name,
                    weight = model.weight,
                    repitition = model.repitition,
                    setNum = model.setNum,
                    restTime = model.restTime,
                    programNo = model.programNo,
                    splitTypeIndex = model.splitTypeIndex
                )
            )
            emit(data)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun getExercises(programNo: Long?, splitIndex: Int?): Flow<List<ExerciseTypeModel>> {
        return flow {
            val data = programDAO.getExercises(programNo, splitIndex)
            val exercises = mutableListOf<ExerciseTypeModel>()
            data.forEach {
                exercises.add(
                    ExerciseTypeModel(
                        no = it.no,
                        name = it.name,
                        weight = it.weight,
                        repitition = it.repitition,
                        setNum = it.setNum,
                        restTime = it.restTime,
                        programNo = it.programNo,
                        splitTypeIndex = it.splitTypeIndex,
                        isPerformed = false
                    )
                )
            }
            emit(exercises)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun getExercisePerformedStatuses(
        programNo: Long?,
        exerciseNo: Long?,
    ): Flow<Int> {
        return flow {
            val data = programDAO.getTodayExercisesPerformedStatuses(
                DateUtil.getCurrentDateForRecord(),
                programNo,
                exerciseNo
            )
            emit(data)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun getTodayExercisePerformed(
        programNo: Long?,
        exerciseNo: Long?,
    ): Flow<List<RecordTable>> {
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

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun getAllRecordsDateByProgramNo(programNo: Long): Flow<List<RecordModel>> {
        return flow {
            val data = programDAO.getAllRecordsDateByProgramNo(programNo)
            emit(data)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun getTargetDateTotalVolume(programNo: Long?, RecordTime: String?): Flow<Int> {
        return flow {
            val data = programDAO.getTargetDateTotalVolume(RecordTime, programNo)
            emit(data)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun getExerciseVolumes(
        programNo: Long,
        recordTime: String,
    ): Flow<List<ExerciseVolumeModel>> {
        return flow {
            val data = programDAO.getExerciseVolumes(programNo, recordTime)
            emit(data)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun getOneDayRecord(programNo: Long?, recordTime: String?): Flow<List<RecordTable>> {
        return flow {
            val data = programDAO.getTargetOneDayRecord(
                recordTime,
                programNo)
            emit(data)
        }
    }

}