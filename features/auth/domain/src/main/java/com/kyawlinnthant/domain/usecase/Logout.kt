package com.kyawlinnthant.domain.usecase

import com.kyawlinnthant.data.repository.AuthDsRepository
import javax.inject.Inject

class Logout @Inject constructor(
    private val repo: AuthDsRepository
) {
    suspend operator fun invoke() {
        repo.alertLogout()
    }
}
