package com.kyawlinnthant.navigation

sealed class Screens(val route: String) {
    data object Login : Screens("login")
    data object ForgotPassword : Screens("forgotPassword")
    data object Surveys : Screens("surveys")
    data object SurveyDetail : Screens("surveyDetail")
}
