package com.kyawlinnthant.domain.usecase

import com.kyawlinnthant.data.repository.AuthDsRepository
import com.kyawlinnthant.domain.form.LoginForm
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIsAuthenticated @Inject constructor(
    private val ds : AuthDsRepository
) {
    suspend operator fun invoke() : Flow<Boolean>{
        return ds.pullIsAuthenticated()
    }
}