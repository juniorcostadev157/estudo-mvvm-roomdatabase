package com.junior.formularioroomdatabase.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.junior.formularioroomdatabase.activity.viewmodel.DetailsTaskViewModel


@Composable
fun DetailTaskScreen(paddingValues: PaddingValues, detailsTaskViewModel: DetailsTaskViewModel){

    LaunchedEffect(detailsTaskViewModel.task) {
        detailsTaskViewModel.loadTask()
    }

    val title by detailsTaskViewModel.title.collectAsState()
    val description by detailsTaskViewModel.description.collectAsState()



    Column(modifier = Modifier.padding(paddingValues).fillMaxSize()){

        Column(
            modifier = Modifier.padding(paddingValues).fillMaxSize()
        ) {
            Text(title, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(10.dp))
            Text(description, modifier = Modifier.weight(1f).fillMaxWidth())
        }
    }

}