package com.kyawlinnthant.data.repository

import com.google.common.truth.Truth.assertThat
import com.kyawlinnthant.pref.PrefDataStore
import com.kyawlinnthant.testrule.CoroutinesTestRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class AuthDsRepositoryTest {
    private lateinit var pref: PrefDataStore
    private var repository: AuthDsRepositoryImpl? = null


    @get:Rule
    val testRule = CoroutinesTestRule()

    @Before
    fun setup() {
        pref = Mockito.mock(PrefDataStore::class.java)

        repository = AuthDsRepositoryImpl(
            ds = pref,
            io = testRule.testDispatcher
        )
    }

    @After
    fun teardown() {
        repository = null
    }

    @Test
    fun `correctly returns isAuthenticated`() = runTest {
        Mockito.`when`(pref.pullIsAuthenticated()).thenReturn(flowOf(true))
        val actual = repository!!.pullIsAuthenticated().first()
        assertThat(actual).isEqualTo(true)
    }
}