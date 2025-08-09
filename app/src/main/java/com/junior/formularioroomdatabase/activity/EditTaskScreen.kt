package com.junior.formularioroomdatabase.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
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
import com.junior.formularioroomdatabase.activity.viewmodel.EditTaskViewModel
import com.junior.formularioroomdatabase.base.Constants

@Composable
fun EditTaskScreen(paddingValues: PaddingValues, editTaskViewModel: EditTaskViewModel) {

    val title by editTaskViewModel.title.collectAsState()
    val description by editTaskViewModel.description.collectAsState()
    val editRequest by editTaskViewModel.isEditRequest.collectAsState()

    LaunchedEffect(editRequest) {
        if (editRequest) editTaskViewModel.editTask()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { editTaskViewModel.setTitle(it) },
            label = {
                Text(
                    Constants.TITULO
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.width(5.dp))
        OutlinedTextField(
            value = description,
            onValueChange = { editTaskViewModel.setDescription(it) },
            label = { Text(Constants.DESCRICAO) },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Spacer(Modifier.height(5.dp))

    }
}



