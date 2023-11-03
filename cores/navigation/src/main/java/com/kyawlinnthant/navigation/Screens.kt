package com.kyawlinnthant.navigation

sealed class Screens(val route: String) {
    companion object {
        const val DETAIL_ID = "id"
        const val DETAIL_NAME = "name"
        const val EMAIL = "email"
    }

    data object Login : Screens("login")
    data object ForgotPassword : Screens("forgotPassword") {
        fun passEmail(email: String) = this.route + "/$email"
        fun getAbsolutePath() = this.route + "/{$EMAIL}"
    }

    data object Surveys : Screens("surveys")
    data object SurveyDetail : Screens("surveyDetail") {
        fun passId(id: String, name: String) = this.route + "/$id/$name"
        fun getAbsolutePath() = this.route + "/{$DETAIL_ID}/{$DETAIL_NAME}"
    }
}
