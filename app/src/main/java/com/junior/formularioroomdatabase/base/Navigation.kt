package com.junior.formularioroomdatabase.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.junior.formularioroomdatabase.data.TaskDataBase

class Navigation {
    private lateinit var localdb: TaskDataBase

    private fun NavGraphBuilder.composableScreen(routes: String){
        composable(routes){
            CallScaffold(navController, localdb).CreateScreen(screen = routes)
        }
    }

    private lateinit var  navController: NavHostController


    @Composable
    fun Create(){
        navController = rememberNavController()
        localdb = TaskDataBase.getDataBase(LocalContext.current)

        NavHost(navController = navController, startDestination = Routes.TaskList.route){

            composableScreen(Routes.TaskList.route)
            composableScreen(Routes.CreateTask.route)
            composableScreen(Routes.EditTask.route)
            composableScreen(Routes.DetailsTask.route)
        }
    }
}