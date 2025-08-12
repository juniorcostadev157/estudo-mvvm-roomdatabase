package com.junior.formularioroomdatabase.activity.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.junior.formularioroomdatabase.base.Routes
import com.junior.formularioroomdatabase.data.db.TaskDataBase
import com.junior.formularioroomdatabase.data.entity.TaskEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateTaskViewModel(

    private val navController: NavController,
    private val localDB: TaskDataBase
):
    ViewModel() {

    private var _title = MutableStateFlow("")
    val title: StateFlow<String> = _title

    private var _description = MutableStateFlow("")
    val description: StateFlow<String> = _description

    private var _isSaveRequest = MutableStateFlow(false)
    val inSaveRequest: StateFlow<Boolean> = _isSaveRequest

    fun createTask(){

        viewModelScope.launch {
            localDB.taskDao().insertAll(TaskEntity(0, _title.value, _description.value))
            Log.i("DATA_BASE_INFO",  "Criando Tarefas:${localDB.taskDao().getAll()}")
        }


        navigate(Routes.TaskList.route)

    }

    private fun navigate(screen:String){
        navController.navigate(screen)
    }

    fun setTitle(title:String){
        _title.value = title
    }

    fun setDescription(description: String){
        _description.value = description
    }
    fun setSaveRequest(isSaveRequest:Boolean){
        _isSaveRequest.value = isSaveRequest
    }
}