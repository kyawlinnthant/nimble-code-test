package com.kyawlinnthant.domain.usecase

import com.google.common.truth.Truth
import com.kyawlinnthant.domain.FakeHomeApiRepository
import com.kyawlinnthant.domain.FakeHomeDbRepository
import com.kyawlinnthant.domain.vo.toVo
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
class GetSurveysTest {
    @get:Rule
    val testRule = CoroutinesTestRule()

    @Test
    fun `get surveys from db successfully done`() = runTest {
        val dao = FakeHomeDbRepository()
        val getSurveys = GetSurveys(
            dao = dao
        )

        Dispatchers.setMain(StandardTestDispatcher())
        dao.saveSurveys(FakeHomeApiRepository.entity)
        val expected = getSurveys.invoke().first()
        advanceUntilIdle()
        Truth.assertThat(expected).isEqualTo(FakeHomeApiRepository.entity.map { it.toVo() })
    }
}
