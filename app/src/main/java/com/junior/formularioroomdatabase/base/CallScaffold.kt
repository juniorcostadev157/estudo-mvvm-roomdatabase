package com.junior.formularioroomdatabase.base

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.junior.formularioroomdatabase.R
import com.junior.formularioroomdatabase.activity.CreateTaskScreen
import com.junior.formularioroomdatabase.activity.DetailTaskScreen
import com.junior.formularioroomdatabase.activity.EditTaskScreen
import com.junior.formularioroomdatabase.activity.ListTaskScreen
import com.junior.formularioroomdatabase.activity.viewmodel.CreateTaskViewModel
import com.junior.formularioroomdatabase.activity.viewmodel.EditTaskViewModel
import com.junior.formularioroomdatabase.activity.viewmodel.ListTaskViewModel
import com.junior.formularioroomdatabase.data.SharedPreferences
import com.junior.formularioroomdatabase.data.TaskDataBase

class CallScaffold (private val navController: NavController, private val localDB:TaskDataBase){

    @SuppressLint("ComposableNaming", "ViewModelConstructorInComposable")
    @Composable
    fun CreateScreen(screen:String): PaddingValues {
        val localData = SharedPreferences(LocalContext.current)
        val listTaskViewModel = ListTaskViewModel(localData, localDB)
        val createTaskViewModel = CreateTaskViewModel(localData, navController, localDB)
        val editTaskViewModel = EditTaskViewModel(localData,  navController, localDB)


        Scaffold(topBar = {
            when(screen){
                Routes.TaskList.route-> ListTopAppBar()
                Routes.CreateTask.route-> CreateTopAppBar(createTaskViewModel)
                Routes.EditTask.route-> EditTopAppBar(editTaskViewModel)
                Routes.DetailsTask.route-> DetailsTopAppBar()
            }

        }) {paddingValues ->
            when(screen){
                Routes.TaskList.route -> ListTaskScreen(paddingValues = paddingValues, navController = navController, listTaskViewModel)
                Routes.CreateTask.route-> CreateTaskScreen(paddingValues = paddingValues,  createTaskViewModel)
                Routes.EditTask.route-> EditTaskScreen(paddingValues = paddingValues, editTaskViewModel)
                Routes.DetailsTask.route-> DetailTaskScreen(paddingValues = paddingValues, localData)
            }
        }
        return PaddingValues()
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun DetailsTopAppBar() {
        CenterAlignedTopAppBar({ Text("Detalhe das Notas") }, navigationIcon = {
            IconButton(onClick = { navController.navigate(Routes.TaskList.route) }) {
                Icon(painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = null)
            }
        })
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun EditTopAppBar(editTaskViewModel: EditTaskViewModel) {
        val context = LocalContext.current
        CenterAlignedTopAppBar({ Text("Editar Notas") }, navigationIcon = {
            IconButton(onClick = { navController.navigate(Routes.TaskList.route) }) {
                Icon(painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = null)
            }
        },
            actions = {
                IconButton(onClick = {
                    editTaskViewModel.setEditRequest(true)
                    Toast.makeText(context, "Edição feita com sucesso", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(Icons.Default.Edit, contentDescription = null)
                }
            }
        )
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun CreateTopAppBar(createTaskViewModel: CreateTaskViewModel) {
        val context = LocalContext.current
        CenterAlignedTopAppBar({ Text("Criar Notas") }, navigationIcon = {
            IconButton(onClick = {
                navController.navigate(Routes.TaskList.route)
            }) { Icon(painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = null) }
        },
            actions = {
                IconButton(onClick = {
                    createTaskViewModel.setSaveRequest(true)
                    Toast.makeText(context, "Dados salvo com sucesso", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(Icons.Default.Done, contentDescription = null)

                }
            }
        )
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun ListTopAppBar() {
        CenterAlignedTopAppBar({ Text("Minhas Notas") })
    }
}