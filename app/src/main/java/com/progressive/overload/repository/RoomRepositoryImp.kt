package com.progressive.overload.repository

import androidx.annotation.WorkerThread
import com.progressive.overload.api.ProgramDAO
import com.progressive.overload.model.entity.ExerciseTypeTable
import com.progressive.overload.model.entity.ProgramTable
import com.progressive.overload.model.entity.RecordTable
import com.progressive.overload.model.model.ExerciseTypeModel
import com.progressive.overload.model.model.ExerciseVolumeModel
import com.progressive.overload.model.model.HomeProgramModel
import com.progressive.overload.model.model.RecordModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by sangyoon on 2021/07/27
 */
@Singleton
class RoomRepositoryImp @Inject constructor(
    private val programDAO: ProgramDAO
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
    override fun getAllProgram2(): Flow<List<HomeProgramModel>> {
        return flow {
            val data = programDAO.getAllProgram2()
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
                    name = model.name!!,
                    weight = model.weight!!,
                    repitition = model.repitition!!,
                    setNum = model.setNum!!,
                    restTime = model.restTime!!,
                    rpe = model.rpe!!,
                    programNo = model.programNo!!,
                    mesoCycleSplitIndex = model.mesoCycleSplitIndex!!,
                    microCycleSplitIndex = model.mesoCycleSplitIndex!!
                )
            )
            emit(data)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun getExercises(programNo: Long, mesoCycleSplitIndex: Int, microCycleSplitIndex: Int): Flow<List<ExerciseTypeModel>> {
        return flow {
            val data = programDAO.getExercises(programNo, mesoCycleSplitIndex, microCycleSplitIndex)
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
                        rpe = it.rpe,
                        programNo = it.programNo,
                        mesoCycleSplitIndex = it.mesoCycleSplitIndex,
                        microCycleSplitIndex = it.microCycleSplitIndex,
                        isPerformed = false
                    )
                )
            }
            emit(exercises)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun getPerformedSets(
        programNo: Long?,
        exerciseNo: Long?,
    ): Flow<Int> {
        return flow {
            val data = programDAO.getPerformedSets(
//                DateUtil.getCurrentDateForRecord(),
                programNo,
                exerciseNo
            )
            emit(data)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun getTargetedExercisePerformed(
        programNo: Long,
        exerciseNo: Long,
    ): Flow<List<RecordTable>> {
        return flow {
            val data = programDAO.getTargetedExercisePerformed(
                programNo,
                exerciseNo)
            emit(data)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun getTargetedExercisePerformed(
        programNo: Long?,
        exerciseNo: Long?,
        targetedDate: String?,
    ): Flow<List<RecordTable>> {
        return flow {
            val data = programDAO.getTargetedExercisePerformed(
                targetedDate,
                programNo,
                exerciseNo)
            emit(data)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun getTargetedAllDate(programNo: Long?, exerciseNo: Long?): Flow<List<String>> {
        return flow {
            val data = programDAO.getTargetedAllDate(programNo, exerciseNo)
            emit(data)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun getPreviousDate(
        targetedProgramNo: Long?,
        targetedExerciseNo: Long?,
        targetedDate: String?
    ): Flow<String> {
        return flow {
            val data = programDAO.getPreviousDate(targetedProgramNo, targetedExerciseNo, targetedDate)
            emit(data)
        }
    }

    override fun getPreviousDate(targetedProgramNo: Long?, targetedDate: String?): Flow<String> {
        return flow {
            val data = programDAO.getPreviousDate(targetedProgramNo, targetedDate)
            emit(data)
        }
    }

    override fun getNextDate(
        targetedProgramNo: Long?,
        targetedExerciseNo: Long?,
        targetedDate: String?,
    ): Flow<String> {
        return flow {
            val data = programDAO.getNextDate(targetedProgramNo, targetedExerciseNo, targetedDate)
            emit(data)
        }
    }

    override fun getNextDate(targetedProgramNo: Long?, targetedDate: String?): Flow<String> {
        return flow {
            val data = programDAO.getNextDate(targetedProgramNo, targetedDate)
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
    override fun updateExercise(exerciseTypeTable: ExerciseTypeTable): Flow<Int> {
        return flow {
            val data = programDAO.updateExercise(exerciseTypeTable)
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
    override fun getOneDayRecord(
        name: String?,
        programNo: Long?,
        recordTime: String?,
    ): Flow<List<RecordTable>> {
        return flow {
            val data = programDAO.getTargetOneDayRecord(
                name,
                recordTime,
                programNo)
            emit(data)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun getOneDayRecordName(programNo: Long?, recordTime: String?): Flow<List<String>> {
        return flow {
            val data = programDAO.getOneDayRecordName(
                recordTime,
                programNo)
            emit(data)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun duplicateProgram(programNo: Long, programName: String): Flow<String> {
        return flow {
            val data = programDAO.duplicateProgram(
                programNo,
                programName)
            emit(data)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun initHyukProgramWeight(
        programNo: Long,
        squart1RM: String,
        dead1RM: String,
        bench1RM: String,
        milp1RM: String,
    ): Flow<Long> {
        return flow {
            val data = programDAO.initHyukProgramWeight(
                programNo,
                squart1RM,
                dead1RM,
                bench1RM,
                milp1RM
            )
            emit(data)
        }
    }
}