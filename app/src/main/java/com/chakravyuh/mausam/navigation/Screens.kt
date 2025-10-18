package com.chakravyuh.mausam.navigation

 sealed  class Screens(val route: String) {
    object HomeScreen : Screens("home_screen")
    object WeeklyReportScreen : Screens("report_screen")

}