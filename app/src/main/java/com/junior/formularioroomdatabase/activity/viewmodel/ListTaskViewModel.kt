package com.junior.formularioroomdatabase.activity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.junior.formularioroomdatabase.data.db.TaskDataBase
import com.junior.formularioroomdatabase.data.entity.TaskEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListTaskViewModel( private val localDB: TaskDataBase): ViewModel() {


    private var _task = MutableStateFlow<List<TaskEntity>>(emptyList())
    val task:StateFlow<List<TaskEntity>> = _task

    private var _showAlertDialog = MutableStateFlow(false)
    var showAlertDialog: StateFlow<Boolean> = _showAlertDialog

    fun loadTask(){
        viewModelScope.launch{
            _task.value = localDB.taskDao().getAll()
        }
    }

    fun deleteTask(task: TaskEntity){
        viewModelScope.launch{
            localDB.taskDao().delete(task)
            _task.value = localDB.taskDao().getAll()
        }
        _showAlertDialog.value = false
    }

    fun setShowAlertDialog(value:Boolean){
        _showAlertDialog.value = value
    }

    fun navigate(screen:String, navController: NavController){
        navController.navigate(screen)
    }
}