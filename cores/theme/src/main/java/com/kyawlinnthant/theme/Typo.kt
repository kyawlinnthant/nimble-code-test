package com.kyawlinnthant.theme


import androidx.compose.material3.Typography
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val fonts = FontFamily(
    Font(R.font.google_sans_bold, weight = FontWeight.Black),
    Font(R.font.google_sans_medium, weight = FontWeight.Medium),
    Font(R.font.google_sans_regular, weight = FontWeight.Normal),
    Font(R.font.google_sans_italic, weight = FontWeight.Thin),
    Font(R.font.google_sans_bold_italic, weight = FontWeight.Bold),
    Font(R.font.google_sans_medium_italic, weight = FontWeight.Medium),
)

val NimbleTypo = Typography(
    displayLarge = TextStyle(fontFamily = fonts),
    displayMedium = TextStyle(fontFamily = fonts),
    displaySmall = TextStyle(fontFamily = fonts),
    headlineLarge = TextStyle(fontFamily = fonts),
    headlineMedium = TextStyle(fontFamily = fonts),
    headlineSmall = TextStyle(fontFamily = fonts),
    titleLarge = TextStyle(fontFamily = fonts),
    titleMedium = TextStyle(fontFamily = fonts),
    titleSmall = TextStyle(fontFamily = fonts),
    bodyLarge = TextStyle(fontFamily = fonts),
    bodyMedium = TextStyle(fontFamily = fonts),
    bodySmall = TextStyle(fontFamily = fonts),
    labelLarge = TextStyle(fontFamily = fonts),
    labelMedium = TextStyle(fontFamily = fonts),
    labelSmall = TextStyle(fontFamily = fonts)
)