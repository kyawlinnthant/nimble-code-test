package com.kyawlinnthant.data.repository


import com.google.common.truth.Truth.assertThat
import com.kyawlinnthant.data.model.SurveyAttribute
import com.kyawlinnthant.data.model.SurveysData
import com.kyawlinnthant.data.model.SurveysResponse
import com.kyawlinnthant.data.service.HomeService
import com.kyawlinnthant.network.util.DataResult
import com.kyawlinnthant.testrule.CoroutinesTestRule
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Response

class HomeApiRepositoryTest {

    private lateinit var service: HomeService
    private var repository: HomeApiRepositoryImpl? = null

    private val mockResponse = SurveysResponse(
        data = listOf(
            SurveysData(
                id = "id",
                type = "type",
                attributes = SurveyAttribute(
                    title = "title",
                    description = "description",
                    aboveThreshold = null,
                    belowThreshold = null,
                    isActive = false,
                    imageUrl = "",
                    createdAt = "createdAt",
                    activeAt = null,
                    surveyType = "surveyType"
                )
            )
        )
    )

    @get:Rule
    val testRule = CoroutinesTestRule()

    @Before
    fun setup() {
        service = mock(HomeService::class.java)

        repository = HomeApiRepositoryImpl(
            api = service,
            io = testRule.testDispatcher
        )
    }

    @After
    fun teardown() {
        repository = null
    }

    @Test
    fun `get surveys successfully transform data`() = runTest {

        //assume with mock
        `when`(
            service.getSurveys(
                pageNumber = 1,
                pageSize = 1,
            )
        ).thenReturn(Response.success(mockResponse))
        val actual = repository!!.getSurveys(
            pageNumber = 1,
            pageSize = 1,
        )
        assertThat(actual as DataResult.Success).isEqualTo(DataResult.Success(mockResponse))
    }

    @Test
    fun `get surveys got error and transform result error`() = runTest {
        `when`(
            service.getSurveys(
                pageNumber = 1,
                pageSize = 1,
            )
        ).thenReturn(
            Response.error(
                400,
                "error response".toResponseBody("txt".toMediaTypeOrNull())
            )
        )
        val actual = repository!!.getSurveys(
            pageNumber = 1,
            pageSize = 1,
        )
        assertThat(actual).isEqualTo(DataResult.Failed("ErrorResponse is null"))
    }
}