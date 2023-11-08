package com.kyawlinnthant.domain.usecase

import com.kyawlinnthant.domain.form.LoginError
import com.kyawlinnthant.domain.form.LoginForm
import com.kyawlinnthant.util.FormValidator
import javax.inject.Inject

class ValidateLogin @Inject constructor() {
    operator fun invoke(
        form: LoginForm
    ): LoginError {
        val isVerifiedEmail = FormValidator.isVerifiedEmail(form.email.trim())
        val isVerifiedPassword = FormValidator.isVerifiedPwd(form.password.trim())
        return LoginError(
            isErrorEmail = !isVerifiedEmail,
            isErrorPassword = !isVerifiedPassword
        )
    }
}
