package com.kyawlinnthant.util

import androidx.core.util.PatternsCompat
import java.util.regex.Pattern

/**
 - at least one Upper letter
 - at least one Lower letter
 - at least one digit
 - at least one special character !@#$%^&+-
 - no space in password
 - at least 6 chars
 - not exceed 24 chars
 */
object FormValidator {

    private const val PWD_MAX_LENGTH = 24
    private const val PWD_MIN_LENGTH = 6
    fun isVerifiedEmail(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isVerifiedPassword(password: String): Boolean {
        if (password.isEmpty()) {
            return false
        }
        if (password.length < PWD_MIN_LENGTH) {
            return false
        }
        if (password.length > PWD_MAX_LENGTH) {
            return false
        }

        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+-])(?=\\S+$).{4,}$"
        val isMatched = Pattern.compile(passwordPattern).matcher(password)
        if (!isMatched.matches()) {
            return false
        }
        return true
    }

}