package com.kyawlinnthant.domain.usecase

import com.google.common.truth.Truth
import com.kyawlinnthant.domain.FakeAuthDsRepository
import com.kyawlinnthant.testrule.CoroutinesTestRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetIsAuthenticatedTest {

    @get:Rule
    val testRule = CoroutinesTestRule()

    @Test
    fun `get isAuthenticated flag successfully done`() = runTest {
        val ds = FakeAuthDsRepository()
        val getFlag = GetIsAuthenticated(
            ds = ds
        )
        Dispatchers.setMain(StandardTestDispatcher())
        ds.putIsAuthenticated(true)
        val expected = getFlag.invoke().first()
        advanceUntilIdle()
        Truth.assertThat(expected).isEqualTo(true)
    }
}
