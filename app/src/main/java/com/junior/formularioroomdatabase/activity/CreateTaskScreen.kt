package com.junior.formularioroomdatabase.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.junior.formularioroomdatabase.activity.viewmodel.CreateTaskViewModel
import com.junior.formularioroomdatabase.base.Constants

@Composable
fun CreateTaskScreen(paddingValues: PaddingValues, createTaskViewModel: CreateTaskViewModel){
    val title by createTaskViewModel.title.collectAsState()
    val description by createTaskViewModel.description.collectAsState()
    val saveRequest by createTaskViewModel.inSaveRequest.collectAsState()


    LaunchedEffect(saveRequest) {
        if (saveRequest)createTaskViewModel.createTask()

    }





    Column  (modifier = Modifier.fillMaxSize().padding(paddingValues).padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally){
        OutlinedTextField(value =title, onValueChange = {createTaskViewModel.setTitle(it)}, label = { Text(
            Constants.TITULO) } , modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.width(5.dp))
        OutlinedTextField(value =description, onValueChange = {createTaskViewModel.setDescription(it)}, label = { Text(Constants.DESCRICAO) }, modifier = Modifier.fillMaxWidth().weight(1f) )
        Spacer(modifier = Modifier.height(5.dp))

    }
}