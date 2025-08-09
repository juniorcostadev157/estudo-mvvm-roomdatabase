package com.junior.formularioroomdatabase.base

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class Navigation {

    private fun NavGraphBuilder.composableScreen(routes: String){
        composable(routes){
            CallScaffold(navController).CreateScreen(screen = routes)
        }
    }

    private lateinit var  navController: NavHostController


    @Composable
    fun Create(){
        navController = rememberNavController()


        NavHost(navController = navController, startDestination = Routes.TaskList.route){

            composableScreen(Routes.TaskList.route)
            composableScreen(Routes.CreateTask.route)
            composableScreen(Routes.EditTask.route)
            composableScreen(Routes.DetailsTask.route)
        }
    }
}