package com.junior.formularioroomdatabase.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
@Dao
interface TaskDAO {
    @Insert
    fun insertAll(vararg taskEntity: TaskEntity)

    @Update
    fun updateAll(taskEntity: TaskEntity)

    @Delete
    fun delete(taskEntity: TaskEntity)

    @Query("SELECT * From taskEntity")
    fun getAll():List<TaskEntity>
}