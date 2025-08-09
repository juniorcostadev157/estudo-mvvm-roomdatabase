package com.junior.formularioroomdatabase.activity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.junior.formularioroomdatabase.base.Constants
import com.junior.formularioroomdatabase.base.Routes
import com.junior.formularioroomdatabase.data.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EditTaskViewModel(private val localData: SharedPreferences, private val navController: NavController):
    ViewModel() {
    private var _title = MutableStateFlow(localData.getPreference(Constants.TITLE_KEY))
    val title: StateFlow<String> = _title

    private var _description = MutableStateFlow(localData.getPreference(Constants.DESCRIPTION_KEY))
    val  description : StateFlow<String> = _description

    private var _isEditRequest = MutableStateFlow(false)
    val isEditRequest: StateFlow<Boolean> = _isEditRequest



    fun editTask(){
        localData.savePreference(Constants.TITLE_KEY, _title.value)
        localData.savePreference(Constants.DESCRIPTION_KEY, _description.value)
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