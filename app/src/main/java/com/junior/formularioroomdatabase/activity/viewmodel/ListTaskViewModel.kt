package com.junior.formularioroomdatabase.activity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.junior.formularioroomdatabase.base.Constants
import com.junior.formularioroomdatabase.data.SharedPreferences
import com.junior.formularioroomdatabase.data.TaskDataBase
import com.junior.formularioroomdatabase.data.TaskEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListTaskViewModel(val localData: SharedPreferences, private val localDB:TaskDataBase): ViewModel() {


    private var _task = MutableStateFlow<List<TaskEntity>>(emptyList())
    val task:StateFlow<List<TaskEntity>> = _task
    private var _title = MutableStateFlow(localData.getPreference(Constants.TITLE_KEY))
    val title: StateFlow<String> = _title
    private var _showAlertDialog = MutableStateFlow(false)
    var showAlertDialog: StateFlow<Boolean> = _showAlertDialog

    fun loadTask(){
        viewModelScope.launch {
            _task.value = localDB.taskDao().getAll()
        }
    }

    fun deleteTask(){
        localData.deletePreference(Constants.TITLE_KEY)
        localData.deletePreference(Constants.DESCRIPTION_KEY)
        _title.value = ""
        _showAlertDialog.value = false
    }

    fun setShowAlertDialog(value:Boolean){
        _showAlertDialog.value = value
    }

    fun navigate(screen:String, navController: NavController){
        navController.navigate(screen)
    }
}