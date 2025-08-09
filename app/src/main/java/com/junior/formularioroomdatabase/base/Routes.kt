package com.junior.formularioroomdatabase.base

sealed class Routes(val route:String) {
    data object TaskList: Routes("taskList")
    data object CreateTask: Routes("taskCreate")
    data object EditTask: Routes("taskEdit")
    data object DetailsTask: Routes("detailsEdit")
}