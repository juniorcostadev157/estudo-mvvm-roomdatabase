package com.junior.formularioroomdatabase.activity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.junior.formularioroomdatabase.base.Constants
import com.junior.formularioroomdatabase.base.Routes
import com.junior.formularioroomdatabase.data.prefs.SharedPreferences
import com.junior.formularioroomdatabase.data.db.TaskDataBase
import com.junior.formularioroomdatabase.data.entity.TaskEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EditTaskViewModel(
    private val localData: SharedPreferences,
    private val navController: NavController,
    private val localDB: TaskDataBase
):
    ViewModel() {

    private var _task = MutableStateFlow(TaskEntity())
    val task:StateFlow<TaskEntity> = _task

    private var _title = MutableStateFlow("")
    val title: StateFlow<String> = _title

    private var _description = MutableStateFlow("")
    val  description : StateFlow<String> = _description

    private var _isEditRequest = MutableStateFlow(false)
    val isEditRequest: StateFlow<Boolean> = _isEditRequest

    fun loadTask(){
        viewModelScope.launch {
            _task.value = localDB.taskDao().getByID(localData.getByIdPreference(Constants.TASK_KEY))
            setTitle(_task.value.title)
            setDescription(_task.value.description)
        }
    }

    fun editTask(){
        viewModelScope.launch {
            localDB.taskDao().update(TaskEntity(_task.value.id, _title.value, _description.value))
        }
        navigate(Routes.TaskList.route)
    }

    private fun navigate(screen:String){
        navController.navigate(screen)
    }
    fun setTitle(title:String){
        _title.value = title
    }
    fun setDescription(description:String){
        _description.value = description
    }

    fun setEditRequest(isEditRequest:Boolean){
        _isEditRequest.value = isEditRequest
    }
}