package com.junior.formularioroomdatabase.activity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junior.formularioroomdatabase.base.Constants
import com.junior.formularioroomdatabase.data.prefs.SharedPreferences
import com.junior.formularioroomdatabase.data.db.TaskDataBase
import com.junior.formularioroomdatabase.data.entity.TaskEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsTaskViewModel(private val localDB: TaskDataBase, private  val localShared: SharedPreferences):ViewModel() {
    private var _task = MutableStateFlow(TaskEntity())
    val task:StateFlow<TaskEntity> = _task

    private var _title = MutableStateFlow(localShared.getPreference(Constants.TITLE_KEY))
    val title:StateFlow<String> = _title

    private var _description = MutableStateFlow(localShared.getPreference(Constants.DESCRIPTION_KEY))
    val description:StateFlow<String> = _description



    fun loadTask(){
        viewModelScope.launch {
            _task.value = localDB.taskDao().getByID(localShared.getByIdPreference(Constants.TASK_KEY))

            _title.value = _task.value.title
            _description.value = _task.value.description
        }
    }
}