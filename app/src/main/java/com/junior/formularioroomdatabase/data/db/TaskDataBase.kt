package com.junior.formularioroomdatabase.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.junior.formularioroomdatabase.data.entity.TaskEntity
import com.junior.formularioroomdatabase.data.local.TaskDAO

@Database(entities = [TaskEntity::class], version = 1)

abstract class TaskDataBase :RoomDatabase(){

    abstract fun taskDao(): TaskDAO

    companion object{

        @Volatile
        private var INSTANCE: TaskDataBase? = null

        fun getDataBase(context: Context): TaskDataBase {
            val  tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instace = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDataBase::class.java,
                    "task_database"
                ).build()
                INSTANCE = instace
                return instace
            }
            }
        }

    }

