package com.kyawlinnthant.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.kyawlinnthant.domain.FakeHomeApiRepository
import com.kyawlinnthant.domain.FakeHomeDbRepository
import com.kyawlinnthant.network.util.DataResult
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
class FetchSurveysTest {
    @get:Rule
    val testRule = CoroutinesTestRule()

    @Test
    fun `fetch successfully and save in db`() = runTest {

        val api = FakeHomeApiRepository()
        val dao = FakeHomeDbRepository()
        val fetchSurveys = FetchSurveys(
            api = api,
            dao = dao
        )

        Dispatchers.setMain(StandardTestDispatcher())
        val expected = fetchSurveys.invoke()
        val dbData = dao.getSurveys().first()
        advanceUntilIdle()
        assertThat(dbData).isEqualTo(FakeHomeApiRepository.entity)
        assertThat(expected).isEqualTo(DataResult.Success(true))
    }
}