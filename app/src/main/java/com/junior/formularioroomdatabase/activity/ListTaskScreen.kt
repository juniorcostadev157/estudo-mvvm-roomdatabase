package com.junior.formularioroomdatabase.activity

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.junior.formularioroomdatabase.activity.viewmodel.ListTaskViewModel
import com.junior.formularioroomdatabase.base.Constants
import com.junior.formularioroomdatabase.base.Routes
import com.junior.formularioroomdatabase.data.SharedPreferences

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun ListTaskScreen(paddingValues: PaddingValues, navController: NavController, listTaskViewModel: ListTaskViewModel) {

    val localData = SharedPreferences(LocalContext.current)


    val title by listTaskViewModel.title.collectAsState(localData.getPreference(Constants.TITLE_KEY))
    val showAlertDialog by listTaskViewModel.showAlertDialog.collectAsState(false)


    Column(
        modifier = Modifier.padding(paddingValues).fillMaxWidth()
    ) {
        if (showAlertDialog){
            AlertDialog(onDismissRequest = {}, confirmButton = { Button(onClick = {
                listTaskViewModel.deleteTask()
            }){
                Text(text = "Sim")
            } }, dismissButton =  {
                Button(onClick = {listTaskViewModel.setShowAlertDialog(false)}) {
                    Text(text = "Não")
                }
            }, text = { Text(text = "Deseja excluir a tarefa?") })
        }

        if (title != ""){
            Card(modifier = Modifier.fillMaxWidth().padding(10.dp).clickable {
                listTaskViewModel.navigate(Routes.DetailsTask.route , navController)

            }) {


                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

                    Text(text = localData.getPreference(Constants.TITLE_KEY), modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp))
                    Box(modifier = Modifier){
                        Row {
                            IconButton(onClick =
                            {

                                listTaskViewModel.setShowAlertDialog(true)

                            }
                            ) {
                                Icon(Icons.Default.Delete, contentDescription = null)
                            }
                            IconButton(onClick = {
                                listTaskViewModel.navigate(Routes.EditTask.route,  navController)

                            }) {
                                Icon(Icons.Default.Edit, contentDescription = null)
                            }
                        }

                    }
                }
            }
        }else{
            Box(modifier = Modifier.fillMaxSize()){
                Text(text = "Não a tasks salvas")
            }
        }

    }

    Box(
        modifier = Modifier.fillMaxSize().padding(paddingValues).padding(10.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(onClick = {
            listTaskViewModel.navigate(Routes.CreateTask.route,navController)
        }) {
            Text(text = "+")
        }
    }
}

