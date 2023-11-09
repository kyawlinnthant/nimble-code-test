package com.kyawlinnthant.network.forbidden

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LogoutAlertModule {
    @Binds
    @Singleton
    fun bindsLogoutAlert(impl: LogoutAlertImpl): LogoutAlert
}
