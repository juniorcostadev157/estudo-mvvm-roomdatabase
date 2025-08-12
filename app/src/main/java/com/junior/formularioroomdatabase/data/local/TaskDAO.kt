package com.junior.formularioroomdatabase.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.junior.formularioroomdatabase.data.entity.TaskEntity

@Dao
interface TaskDAO {
    @Insert
    suspend fun insertAll(vararg taskEntity: TaskEntity)

    @Update
    suspend fun update(taskEntity: TaskEntity)

    @Delete
    suspend fun delete(taskEntity: TaskEntity)

    @Query("SELECT * From taskEntity")
   suspend fun getAll():List<TaskEntity>

    @Query("SELECT * From taskEntity where id like(:id)")
    suspend fun getByID(id:Long): TaskEntity
}