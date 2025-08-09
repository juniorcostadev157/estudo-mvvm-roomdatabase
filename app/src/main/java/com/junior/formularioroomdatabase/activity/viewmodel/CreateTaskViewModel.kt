package com.junior.formularioroomdatabase.activity.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.junior.formularioroomdatabase.base.Constants
import com.junior.formularioroomdatabase.base.Routes
import com.junior.formularioroomdatabase.data.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CreateTaskViewModel(private val localData: SharedPreferences, private val navController: NavController):
    ViewModel() {

    private var _title = MutableStateFlow(localData.getPreference(Constants.TITLE_KEY))
    val title: StateFlow<String> = _title

    private var _description = MutableStateFlow(localData.getPreference(Constants.DESCRIPTION_KEY))
    val description: StateFlow<String> = _description

    private var _isSaveRequest = MutableStateFlow(false)
    val inSaveRequest: StateFlow<Boolean> = _isSaveRequest

    fun createTask(){
        localData.savePreference(Constants.TITLE_KEY, _title.value)
        localData.savePreference(Constants.DESCRIPTION_KEY, _description.value)

        Log.i("Informação", "A descrição é: ${localData.getPreference(_title.value)}")

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