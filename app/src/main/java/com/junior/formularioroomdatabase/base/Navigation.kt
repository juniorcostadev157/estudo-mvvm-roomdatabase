package com.junior.formularioroomdatabase.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.junior.formularioroomdatabase.data.prefs.SharedPreferences
import com.junior.formularioroomdatabase.data.db.TaskDataBase

class Navigation {
    private lateinit var localdb: TaskDataBase
    private lateinit var navController: NavHostController

    private fun NavGraphBuilder.composableScreen(
        routes: String,
        navController: NavHostController,
        localdb: TaskDataBase,
        localData: SharedPreferences
    ) {
        composable(routes) {
            CallScaffold(navController, localdb, localData).CreateScreen(screen = routes)
        }
    }

    @Composable
    fun Create() {
        navController = rememberNavController()
        val context = LocalContext.current

        localdb = TaskDataBase.getDataBase(context)
        val localData = SharedPreferences(context)

        NavHost(navController = navController, startDestination = Routes.TaskList.route) {
            composableScreen(Routes.TaskList.route, navController, localdb, localData)
            composableScreen(Routes.CreateTask.route, navController, localdb, localData)
            composableScreen(Routes.EditTask.route, navController, localdb, localData)
            composableScreen(Routes.DetailsTask.route, navController, localdb, localData)
        }
    }
}
