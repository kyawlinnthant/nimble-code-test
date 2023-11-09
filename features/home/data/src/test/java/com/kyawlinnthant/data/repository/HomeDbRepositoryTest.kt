package com.kyawlinnthant.data.repository

import com.google.common.truth.Truth
import com.kyawlinnthant.database.dao.SurveyDao
import com.kyawlinnthant.database.entity.SurveyEntity
import com.kyawlinnthant.testrule.CoroutinesTestRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class HomeDbRepositoryTest {

    private lateinit var dao: SurveyDao
    private var repository: HomeDbRepositoryImpl? = null

    private val mockResponse = listOf(
        SurveyEntity(
            id = "id",
            name = "name",
            description = "description",
            image = "image",
            createdAt = "created",
            activeAt = "activeAt"
        )
    )

    @get:Rule
    val testRule = CoroutinesTestRule()

    @Before
    fun setup() {
        dao = Mockito.mock(SurveyDao::class.java)

        repository = HomeDbRepositoryImpl(
            dao = dao,
            io = testRule.testDispatcher
        )
    }

    @After
    fun teardown() {
        repository = null
    }

    @Test
    fun `save surveys successfully save in database`() = runTest {
        Mockito.`when`(dao.insertSurveys(mockResponse)).thenReturn(Unit)
        Mockito.`when`(dao.readSurveys()).thenReturn(flowOf(mockResponse))

        val actual = repository!!.getSurveys().first()

        Truth.assertThat(actual).isEqualTo(mockResponse)
    }
}
