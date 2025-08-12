package com.junior.formularioroomdatabase.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "taskEntity")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0L,
    @ColumnInfo("title")
    var title:String = "",
    @ColumnInfo("content")
    var description:String = "",

)
