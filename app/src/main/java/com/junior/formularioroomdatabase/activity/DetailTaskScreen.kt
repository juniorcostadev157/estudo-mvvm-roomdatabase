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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.junior.formularioroomdatabase.base.Constants
import com.junior.formularioroomdatabase.data.SharedPreferences

@Composable
fun DetailTaskScreen(paddingValues: PaddingValues, localData: SharedPreferences){

    Column(modifier = Modifier.padding(paddingValues).fillMaxSize()){
        val title by remember { mutableStateOf(localData.getPreference(Constants.TITLE_KEY)) }
        val description by remember { mutableStateOf(localData.getPreference(Constants.DESCRIPTION_KEY)) }
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxSize()
        ) {
            Text(title, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(10.dp))
            Text(description, modifier = Modifier.weight(1f).fillMaxWidth())
        }
    }

}